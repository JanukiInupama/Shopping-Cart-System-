import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WestminsterShoppingManager {               // Main shopping manager class
    public static ShoppingCart productList = new ShoppingCart();            // Shopping cart instance
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        loadFile();                               // Load existing products

        while (true) {
            System.out.println("1.Add Product");
            System.out.println("2.remove Product");
            System.out.println("3.display all Products");
            System.out.println("4.save");
            System.out.println("0.exit");


            int number = input.nextInt();                        // Get user choice

            switch (number) {                             // Act based on user choice
                case 0:
                    return;
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    displayProduct();
                    break;
                case 4:
                    savefile();
                    break;
                case 5:
                    new WestminsterFrame();
                    break;
                default:
                    System.out.println("wrong input");
            }
        }
    }


    private static void savefile() {          // Save products to file
        try {
            FileWriter productFile = new FileWriter("savedProduct.txt");                          // Write products to a file
            for (int i = 0; i < productList.getProductList().size(); i++) {
                productFile.write(productList.getProductList().get(i).displayProducts() + "\n");
            }
            productFile.close();
            System.out.println("Products saved successfully in saveProducts.txt");
        } catch (IOException e) {
            System.out.println("An error occurred!");
        }

    }


    private static void deleteProduct() {                  // Delete a product from the shopping cart

        System.out.println("Choose the product type");
        System.out.println("1.Clothing");
        System.out.println("2.Electronic");
        int productType = input.nextInt();

        System.out.println("Enter Product ID: ");
        String productToDelete = input.next();

        boolean deletedItem = false;

        switch (productType) {        // Iterate through products and delete if found
            case 1:
                for (int i = 0; i < productList.getProductList().size(); i++) {
                    if (productToDelete.equals(productList.getProductList().get(i).getProductId()) && productList.getProductList().get(i).getProductType().equals("Clothing")) {
                        System.out.println(productList.getProductList().get(i).displayProducts());
                        productList.removeProduct(productList.getProductList().get(i));
                        deletedItem = true;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < productList.getProductList().size(); i++) {
                    if (productToDelete.equals(productList.getProductList().get(i).getProductId()) && productList.getProductList().get(i).getProductType().equals("Electronic")) {
                        System.out.println(productList.getProductList().get(i).displayProducts());
                        productList.removeProduct(productList.getProductList().get(i));
                        deletedItem = true;
                    }
                }
                break;
            default:
                System.out.println("invalid product type");
        }
        if (deletedItem) { // Display result
            System.out.println("Product deleted successfully!");
            System.out.println("Items Left in the Cart : " + productList.getProductList().size());

        } else {
            System.out.println("Product not found!");
        }


    }

    public static void addProduct() {
        System.out.println("Enter the productID");
        String ProductId = input.next();
        System.out.println("Enter the product Name");
        String ProductName = input.next();
        System.out.println("Enter the NoOfItems");
        int Numberofavailableitems = input.nextInt();
        System.out.println("Enter the Price");
        double price = input.nextDouble();

        System.out.println("Enter the products want");
        System.out.println("1.Clothing");
        System.out.println("2.Electronic");
        int productType = input.nextInt();

        // Add new product based on type
        if (productType == 1) {
            System.out.println("Enter the size");
            String size = input.next();
            System.out.println("Enter the color");
            String color = input.next();

            Clothing clothing = new Clothing(ProductId, ProductName, Numberofavailableitems, price, "Clothing", size, color);
            productList.addProduct(clothing);
        } else if (productType == 2) {
            System.out.println("Enter the brand");
            String brand = input.next();
            System.out.println("Enter the warranty Period");
            int warrentyperiod = input.nextInt();

            Electronics electronics = new Electronics(ProductId, ProductName, Numberofavailableitems, price, "Electronic", brand, warrentyperiod);
            productList.addProduct(electronics);
        } else {
            System.out.println("Invalid product type");
        }

    }
    // Display all products in the shopping cart
    private static void displayProduct() {
        // Check if the cart is empty
        if (productList.getProductList().isEmpty()) {
            System.out.println("Product cart is Empty");
        } else {
            // Display each product
            for (int i = 0; i < productList.getProductList().size(); i++) {
                System.out.println(productList.getProductList().get(i).displayProducts());
            }
        }
    }
    // Load products from file
    public static void loadFile() {
        try {
            File saveFile = new File("savedProduct.txt");
            Scanner fileReader = new Scanner(saveFile);
            while (fileReader.hasNext()){
                String dataLine = fileReader.nextLine();
                String[] dataArray = dataLine.split("\\|");

                    // Create product objects based on data
                if (dataArray.length == 7){
                    if (dataArray[6].equals("Clothing")){
                        Clothing clothing = new Clothing(dataArray[0],dataArray[1],Integer.parseInt(dataArray[2]),Double.parseDouble(dataArray[3]),"Clothing",dataArray[4],dataArray[5]);
                        productList.addProduct(clothing);
                    } else if (dataArray[6].equals("Electronic")) {
                        Electronics electronics = new Electronics(dataArray[0],dataArray[1],Integer.parseInt(dataArray[2]),Double.parseDouble(dataArray[3]),"Electronic",dataArray[4],Integer.parseInt(dataArray[5]));
                        productList.addProduct(electronics);
                    }
                }else{
                    System.out.println("Invalid file format.");
                }
            }
            System.out.println("Loaded products from the saved File");
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
