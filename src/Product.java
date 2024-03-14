// Abstract class representing a product
public abstract class Product implements Comparable<Product>{
    private String ProductId;
    private String ProductName;
    private int Numberofavailableitems;
    private double Price;
    private String productType;

    // Constructor to initialize product attributes
    public Product(String productId,String productName,int numberofavailableitems,double price, String productType){
        this.ProductId = productId;
        this.ProductName = productName;
        this.Numberofavailableitems = numberofavailableitems;
        this.Price = price;
        this.productType = productType;
    }
    // Getter methods for accessing product attributes
    public double getPrice(){
        return Price;
    }
    public String getProductId(){
        return ProductId;
    }
    public String getProductType(){
        return productType;
    }
    public String getProductName(){
        return ProductName;
    }
    public int getNumberofavailableitems(){
        return Numberofavailableitems;
    }
    public int getWarrantyPeriod(){
        return getWarrantyPeriod();
    }
    public String getBrand(){
        return getBrand();
    }
    public String getSize(){
        return getSize();
    }
    public String getColour(){
        return getColour();
    }
    // Abstract method to display product details
    abstract String displayProducts();

    // Comparable interface method to compare products based on ProductId
    public int compareTo(Product product){
        return ProductId.compareTo(product.ProductId);
    }
    // Setter method to update the number of available items
    public void setNumberOfItems(int quantity) {
        this.Numberofavailableitems = quantity;
    }
}
