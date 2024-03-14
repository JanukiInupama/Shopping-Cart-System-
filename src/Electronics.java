// Class representing Electronics product, extending the Product class
public class Electronics extends Product{
    private String brand;
    private int warrentyPeriod;

    // Constructor to initialize Electronics attributes and call the superclass constructor
    public Electronics(String productId, String productName, int numberofavailableitems, double price, String productType, String brand, int warrantyperiod){
        super(productId,productName,numberofavailableitems,price,productType);
        this.brand =brand;
        this.warrentyPeriod =warrantyperiod;
    }

    // Getter method for accessing the brand attribute
    public String getBrand() {
        return brand;
    }

    // Getter method for accessing the warrantyPeriod attribute
    public int getWarrantyPeriod() {
        return warrentyPeriod;
    }
    // Method to display product information
    public String displayProducts() {
        return (getProductId()+"|"+getProductName()+"|"+ getNumberofavailableitems()+"|"+getPrice()+"|"+getBrand()+"|"+getWarrantyPeriod()+"|"+getProductType());
    }

}
