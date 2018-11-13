Message Processing Application
This is a small application for processing sales notification messages.

Assumptions made: 
1) Notification messages are stored in a file of JSON format.
2) There is no API calls and the file name of  income messages with paths are obtained from command line
4) JSON format have been predefined with sender side  

The application meets the initial requirements on processing:
- All sales are recorded. In application we use Record class to store a record details and a HashMap to store all the records with key of product name.
- All messages are processed. Application uses external library JACKSON to parse JSON file into Message class and its child classes for messages of type 2 and 3.
- After every 10th message received the application logs a report detailing the number
of sales of each product and their total value. This is done in MessageProcessor class.
- After 50 messages the application logs that it is pausing, stops accepting new
messages and logs a report of the adjustments that have been made to each sale type while
the application was running. This is also 

Requierements on Sales and Message types:
- A sale has a product type field and a value. 
- Any number of different product types can be expected. There is no fixed set.
- A sale notification message could be one of the following types:
1) Message Type 1 - contains the details of 1 sale E.g apple at 10p
2) Message Type 2 - contains the details of a sale and the number of occurrences of
that sale. E.g 20 sales of apples at 10p each.
3) Message Type 3 – contains the details of a sale and an adjustment operation to be
applied to all stored sales of this product type. Operations can be add, subtract, or
multiply e.g Add 20p apples would instruct your application to add 20p to each sale
of apples you have recorded.


RUNNING THE APPLICATION
Commandline Arguments: /files/notificationmessages.json



