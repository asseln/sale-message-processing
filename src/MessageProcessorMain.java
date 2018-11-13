

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import messages.MessageWithOccurrences;
import messages.MessageWithOperationtype;
import messages.SaleMessage;


public class MessageProcessorMain {
    public static void main(String[] args) {
  
        if(isInvalidFilePath(args[0])) {
            System.out.println("Given file path(s) is (/are) either incorrect or inaccessible. Please confirm and re-run.");
            System.exit(1);
        }

        String notificationsFile = args[0];

        MessageProcessor messageProcessor = MessageProcessor.getMessageProcessor();

        List<SaleMessage> messages = messageProcessor.parseNotificationMessage(notificationsFile);
        if(messages == null) {
            System.out.println("Parsing the file with notification messages has been failed.");
            System.exit(1);
        }

        boolean processed = messageProcessor.process(messages);
        if(!processed) {
            System.out.println("Parsing the file with notification messages has been failed.");
            System.exit(1);
        }
    }

    private static boolean isInvalidFilePath(String filePath) {
        try {
            Path path = Paths.get(filePath);

            if(!Files.exists(path) || Files.notExists(path)) { //either does not exist or status is unknown
                return true;
            }

            if(!Files.isRegularFile(path)) { //an executable or directory
                return true;
            }

            if(!Files.isReadable(path)) { //not allowed to read (at least at this moment)
                return true;
            }
        } catch (InvalidPathException | NullPointerException exception) {
            return true; //raised an exception
        }

        return false;
    }
}
