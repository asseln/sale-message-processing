


import messages.OperationType;
import messages.MessageWithOccurrences;
import messages.MessageWithOperationtype;
import messages.SaleMessage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Date;



public class MessageProcessor {
    
    
    private static MessageProcessor messageProcessor = new MessageProcessor();
    private RecordRegistrator register; //refactor later for auditing and stuff as application grows

    private static final int MESSAGE_PROCESSING_MAX = 50;
    private static final int MESSAGE_REPORT_LOG_AFTER = 10;

    private MessageProcessor() {
        this.register = new RecordRegistrator();
    }

    public static MessageProcessor getMessageProcessor() {
        return messageProcessor;
    }

  
    public List<SaleMessage> parseNotificationMessage(String notificationsFile){
        List<SaleMessage> messages = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            messages = mapper.readValue(new File(notificationsFile), new TypeReference<List<SaleMessage>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }
 
    public boolean process(List<SaleMessage> messages) {
        int messageCounter = 0;
        StringBuilder adjustmentsLog = new StringBuilder();

        for(SaleMessage message : messages) {
                      
            boolean recordsUpdated = register.addRecords(message);
            if(!recordsUpdated) {
                return false;
            }
            
            messageCounter++;
            

            if(message instanceof MessageWithOperationtype) {
                //update records on sale with adjustment operation
                register.adjustTransactions(message);
                //write to adjustment log
                adjustmentsLog.append(new Date()+": " + "Product name: " + message.getProductName()+ "; "
                        + "Selling price: " + message.getSellingPrice() + "; " 
                        + "Operation adjusted: " + ((MessageWithOperationtype) message).getOperationType() + ".\n");
              
            }
            
            
            //After every 10th message received the application logs a report detailing the number of sales of each product and their total value
    
            if(messageCounter % 10 == 0) {
                
                System.out.println("\n*** Intermediate Processed Sales' Record ***");
                register.printSalesReport();
            }
            
            // After 50 messages the application prints adjustments log
            if(messageCounter == MESSAGE_PROCESSING_MAX) {
                System.out.println("\nApplication reached its limited number of messages to be processed - "
                        + MESSAGE_PROCESSING_MAX + "! Message processing has stopped.");
                System.out.println("\nAdjustments Log: ");
                System.out.println(adjustmentsLog.toString());
                break;
            }
            
        }


        return true;
    }
}
