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
public class MessageWithOccurrences extends SaleMessage {
       private int numOccurences;
    
    //Default constructor  
    public MessageWithOccurrences() {
      
    }
    
    //Default constructor in input parameters specific for this class
    public MessageWithOccurrences(int numOccurences){
        this.numOccurences = numOccurences;
    }
    
   
        
    public MessageWithOccurrences(String productType, String productName, Double sellingPrice, int numOccurences) {
       super(productType, productName, sellingPrice);
       this.numOccurences = numOccurences;
    }
    
     
      public int getNumOccurences() {
        return numOccurences;
    }

    public void setNumOccurences(int numOccurences) {
        this.numOccurences = numOccurences;
    }
}
