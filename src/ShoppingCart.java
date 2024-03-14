import java.util.ArrayList;
import java.util.Collections;
// Represents a shopping cart that holds a list of products
public class ShoppingCart {
    private ArrayList<Product> productList;

    // Represents a shopping cart that holds a list of products
    public double firstDiscountVal;
    public double threeItemDiscountVal;

    // Constructor to initialize the shopping cart
    public ShoppingCart(){
        this.productList = new ArrayList<>();
    }

    // Method to add a product to the shopping cart
    public void addProduct(Product product){
        productList.add(product);
    }
    // Method to remove a product from the shopping cart
    public void removeProduct(Product product){
        productList.remove(product);
    }
    // Method to calculate the total cost of items in the shopping car
    public double totalCost(){
        double totalCost = 0;
        for (int i = 0; i < productList.size(); i++) {
            System.out.println("Number Of Items : " + productList.get(i).getNumberofavailableitems());
            totalCost = totalCost + productList.get(i).getPrice()*productList.get(i).getNumberofavailableitems();
        }
        return totalCost;
    }

    // Method to calculate the first discount based on whether it's a new account
    public double firstDiscount(Boolean newAccount){
        if(newAccount){
            firstDiscountVal = totalCost()*0.1;
        }
        System.out.println("firstDiscountVal - " + firstDiscountVal);
        return firstDiscountVal;
    }
    // Method to calculate the three-item discount
    public double threeItemDiscount(){
        threeItemDiscountVal = totalCost() * 0.2;
        System.out.println("threeItemDiscountVal - " + threeItemDiscountVal);
        return threeItemDiscountVal;
    }


    // Method to calculate the final total value considering discounts
    public double finalTotValue(){
        double finalTotValue = totalCost()  - firstDiscountVal -threeItemDiscountVal;
        System.out.println("finalTotValue - " + finalTotValue);
        System.out.println("totalCost - "+ totalCost());
        return finalTotValue;
    }

    // Method to get the sorted list of products in the shopping cart
    public ArrayList<Product> getProductList(){
        Collections.sort(productList);
        return productList;
    }
}
