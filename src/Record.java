
public class Record {
    private String productType;
    private String productName;
    private double sellingPrice; 
    private int numSales;
    
    public Record(String productType, String productName, double sellingPrice, int numSales){
       this.productType = productType;
       this.productName = productName;
       this.sellingPrice = sellingPrice; 
       this.numSales = numSales;
    }
    public String getProductType()
    {
        return productType;
    }
     
     public void setProductName(String productType)
    {
         this.productName = productName;
    }
    
     public String getProductName()
    {
        return productName;
    }
     
     public void setProductType(String productType)
    {
         this.productType = productType;
    }
    
     
    public int getNumSales()
    {
        return numSales;
    }
    
    public void setNumSales(int numSales)
    {
        this.numSales = numSales;
    }
    
    
    
    public void setSellingPrice(double newPrice)
    {
        this.sellingPrice = newPrice;
    }
    
    public double getSellingPrice()
    {
        return sellingPrice;
    }
    
        
        public double totalSales()
    {
        return sellingPrice*numSales;
    }
}
