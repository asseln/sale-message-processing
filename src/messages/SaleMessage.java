package messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MessageWithOccurrences.class),
        @JsonSubTypes.Type(value = MessageWithOperationtype.class)
})

public class SaleMessage{
    
   private String productType;
   private String productName;
    private double sellingPrice;
  
    //Default constructor
    public SaleMessage(){
        
    }
    
    //Constructor with 3 input parameters
    public SaleMessage(String productType, String productName, double sellingPrice) {
        this.productType = productType;
        this.sellingPrice = sellingPrice;
        this.productName = productName;
    }
 
 
    public String getProductType() {
        return productType;
    }

    public void getProductType(String productType) {
        this.productType = productType;
    }
    
    public String getProductName() {
        return productName;
    }

    public void getProductName(String productName) {
        this.productName = productName;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }  
    
    
}
