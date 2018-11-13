

import messages.OperationType;
import messages.SaleMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import messages.MessageWithOccurrences;
import messages.MessageWithOperationtype;

public class RecordRegistrator {
    
    Map<String, List<Record>> records;

    public RecordRegistrator() {
        this.records = new HashMap<>();
    }

    public RecordRegistrator(Map<String, List<Record>> records) {
        this.records = records;
    }

    public Map<String, List<Record>> getRecords() {
        return records;
    }

    public boolean addRecords(SaleMessage message) {
        int numOccurences = 1;
        if (message instanceof MessageWithOccurrences){
            numOccurences = ((MessageWithOccurrences) message).getNumOccurences();
        }
            
        Record record = new Record(message.getProductType(), message.getProductName(), message.getSellingPrice(), numOccurences);
       
        List<Record> productRecords = records.get(message.getProductName());
        if (productRecords == null){
                productRecords = new ArrayList<>();
        }
        productRecords.add(record);
        records.put(message.getProductName(), productRecords);
 
        return true;
    }
    
        public void adjustTransactions(SaleMessage message) {
        OperationType operationType = ((MessageWithOperationtype) message).getOperationType();
        List<Record> productRecords = records.get(message.getProductName());
        switch(operationType) {
            case ADD:
                for(Record record : productRecords) {
                    record.setSellingPrice(record.getSellingPrice() + message.getSellingPrice());
                }
                break;
            case MULTIPLY:
                for(Record record : productRecords) {
                    record.setSellingPrice(record.getSellingPrice() * message.getSellingPrice());
                }
                break;
            case SUBTRACT:
                for(Record record : productRecords) {
                    record.setSellingPrice(record.getSellingPrice() + message.getSellingPrice());
                }
                break;
            default:
                System.out.println("Operation is not defined!");
                break;
        }

        
    }

   
    public void printSalesReport() {
        for(Map.Entry<String, List<Record>> record : records.entrySet()) {
            System.out.println("Product type: " + record.getKey() +
                    ", Total product units sold: " + getTotalSales(record.getValue()) +
                    ", Total revenue generated: " + getTotalRevenue(record.getValue())
            );
        }
    }
    
    public int getTotalSales(List<Record> salesRecords){
       int totalNumSales = 0;
        for (Record record : salesRecords)
       {
           totalNumSales+=record.getNumSales();
       }
        return totalNumSales;
    }

    
    private double getTotalRevenue(List<Record> salesRecords) {
        double revenueGenerated = 0;

        for (Record record : salesRecords)
       {
           revenueGenerated+=record.getNumSales();
       }
        
        return revenueGenerated;
    }

}
