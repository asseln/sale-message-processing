package messages;

import messages.SaleMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Malik Fernandez
 */
public class MessageWithOperationtype extends SaleMessage {
    OperationType operationType;
    
    //Default constructor
    public MessageWithOperationtype(){
        
        
    }
    
    //Default constructor with a parameter specific for this class
    public MessageWithOperationtype(OperationType operationType){
        this.operationType = operationType;
    }
    
    public MessageWithOperationtype(String productType, String productName, Double sellingPrice, OperationType operationType) {
        super(productType, productName, sellingPrice);
        this.operationType = operationType;
    }
     
    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }  
}
