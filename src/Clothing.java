// Class representing Clothing product, extending the Product class
public class Clothing extends Product {
    private String size;
    private String colour;

    // Constructor to initialize Clothing attributes and call the superclass constructor
    public Clothing(String productId, String productName, int numberofavailableitems, double price, String productType, String size, String colour) {
        super(productId, productName, numberofavailableitems, price,productType);
        this.colour = colour;
        this.size = size;
    }

    // Getter method for accessing the size attribute
    public String getSize() {
        return size;
    }

    // Getter method for accessing the colour attribute
    public String getColour() {
        return colour;
    }

    // Method to display product information
    public String displayProducts() {
        return (getProductId()+"|"+getProductName()+"|"+ getNumberofavailableitems()+"|"+getPrice()+"|"+getSize()+"|"+getColour()+"|"+getProductType());
    }
}
