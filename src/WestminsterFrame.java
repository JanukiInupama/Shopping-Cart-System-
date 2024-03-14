import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class WestminsterFrame {
    public static ShoppingCart userCart = new ShoppingCart();

    public JFrame westminsterFrame = new JFrame("Westminster Shopping Center");

    public static JPanel topPanel, bottomPanel,
            dropPanel, dropPanel1,dropPanel2,dropPanel3
            ,bottomPanel1, bottomPanel2;


    public static JTable table;
    public static JScrollPane scrollPane;
    public static String dropdownOption;
    public static Product selectedProduct;
    public static JLabel productIDLabel, categoryLabel, nameLabel, availableItemLabel,otherLabel1, otherLabel2;


    public WestminsterFrame(){
        westminsterFrame.setSize(800,700);
        westminsterFrame.setLayout(new GridLayout(2,1));

        topPanel = new JPanel(new GridLayout(2,1));
        bottomPanel = new JPanel(new GridLayout(1,2));
        dropPanel = new JPanel(new GridLayout(1,3));

        dropPanel1 = new JPanel(new GridBagLayout());
        dropPanel2 = new JPanel(new GridBagLayout());
        dropPanel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        bottomPanel1 = new JPanel();
        bottomPanel2 = new JPanel();

        bottomPanel1.setLayout(new BoxLayout(bottomPanel1,BoxLayout.Y_AXIS));

        int marinSize = 8;
        EmptyBorder emptyBorder = new EmptyBorder(marinSize, marinSize*5, marinSize,0);

        productIDLabel = new JLabel("Product ID - ");
        nameLabel = new JLabel("Name - ");
        categoryLabel = new JLabel("Category - ");
        availableItemLabel= new JLabel("Available Item - ");
        otherLabel1 = new JLabel("Size - ");
        otherLabel2 = new JLabel("Color - ");

        bottomPanel1.setBorder(emptyBorder);
        productIDLabel.setBorder(emptyBorder);
        nameLabel.setBorder(emptyBorder);
        categoryLabel.setBorder(emptyBorder);
        availableItemLabel.setBorder(emptyBorder);
        otherLabel1.setBorder(emptyBorder);
        otherLabel2.setBorder(emptyBorder);


        JLabel topPanelText = new JLabel("Select Product Category");
        JLabel bottomPanelText = new JLabel("Select Product Details");

        String[] dropdownList = {"All", "Electronic", "Clothing"};
        JComboBox<String> dropdownMenu = new JComboBox<>(dropdownList);
        dropdownMenu.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXX");

        dropdownOption = (String) dropdownMenu.getSelectedItem();

        JButton shoppingCartButton = new JButton("Shopping Cart");
        JButton addToShoppingCartButton = new JButton("Add to Shopping Cart");
        bottomPanel2.add(addToShoppingCartButton);

        dropPanel1.add(topPanelText);
        dropPanel2.add(dropdownMenu);
        dropPanel3.add(shoppingCartButton);

        dropPanel.add(dropPanel1);
        dropPanel.add(dropPanel2);
        dropPanel.add(dropPanel3);

        table = createTable(WestminsterShoppingManager.productList.getProductList());
        scrollPane = new JScrollPane(table);



        topPanel.add(dropPanel);
        topPanel.add(scrollPane);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //System.out.println("item selected");
                if (!e.getValueIsAdjusting()){
                    int SelectedRow = table.getSelectedRow();
                    System.out.println("Item selected" + SelectedRow);
                    if(SelectedRow != -1){
                        int modelRow = table.convertRowIndexToModel(SelectedRow);
                        selectedProduct = getProductList(WestminsterShoppingManager.productList.getProductList(),dropdownOption).get(modelRow);
                        System.out.println(selectedProduct.displayProducts());
                        updateDisplayPanel(selectedProduct);
                    }
                }
            }
        });

        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShoppingCartFrame().updateInformation();
            }
        });
        addToShoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
 //               System.out.println(selectedProduct.displayProducts());
                if(selectedProduct != null){
 //                   System.out.println(selectedProduct.displayProducts());
                    userCart.addProduct(selectedProduct);
                    try{
                        ShoppingCartFrame.updateInformation();
                        ShoppingCartFrame.updateTableModel();
                    }catch (NullPointerException ignored){}
                }
            }
        });

        bottomPanel1.add(bottomPanelText);
        bottomPanel1.add(productIDLabel);
        bottomPanel1.add(categoryLabel);
        bottomPanel1.add(nameLabel);
        bottomPanel1.add(otherLabel1);
        bottomPanel1.add(otherLabel2);
        bottomPanel1.add(availableItemLabel);

        bottomPanel.add(bottomPanel1);
        bottomPanel.add(bottomPanel2);

        westminsterFrame.add(topPanel);
        westminsterFrame.add(bottomPanel);

        dropdownMenu.addActionListener(e -> {
            dropdownOption = (String) dropdownMenu.getSelectedItem();
            System.out.println(dropdownOption);
            updateTableModel();
            topPanel.revalidate();
        });

        westminsterFrame.setVisible(true);
    }



    public static JTable createTable(ArrayList<Product> productsList){
        String[] columnNames ={"Product ID", "Name", "Category", "Price $", "Info"};
        DefaultTableModel model = new DefaultTableModel(columnNames,0);

        for (Product product:productsList){
           System.out.println(product.displayProducts());
            switch (product.getProductType()){
                case "Electronic":
                    Object[] rowDataElectronic = {product.getProductId(),product.getProductName(),product.getProductType(),product.getPrice(),product.getBrand(),product.getWarrantyPeriod() + "months warranty"};
                    model.addRow(rowDataElectronic);
                    break;
                case "Clothing":
                    Object[] rowDataClothing = {product.getProductId(),product.getProductName(),product.getProductType(),product.getPrice(),product.getColour(),product.getSize()};
                    model.addRow(rowDataClothing);
                    break;
            }
        }

        JTable table = new JTable(model);
        table.setDefaultEditor(Object.class,null);
        return table;
    }




    public static void updateTableModel(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        ArrayList<Product> productList = getProductList(WestminsterShoppingManager.productList.getProductList(),dropdownOption);

        for(Product product: productList){
            System.out.println(product.displayProducts());
           switch (product.getProductType()){
                case "Electronic":
                    Object[] rowDataElectronic = {product.getProductId(),product.getProductName(),product.getProductType(),product.getPrice(),product.getBrand(),product.getWarrantyPeriod() + "months warranty"};
                    model.addRow(rowDataElectronic);
                    break;
                case "Clothing":
                    Object[] rowDataClothing = {product.getProductId(),product.getProductName(),product.getProductType(),product.getPrice(),product.getColour(),product.getSize()};
                    model.addRow(rowDataClothing);
                    break;
            }
        }
    }

    public static void updateDisplayPanel(Product product){
        System.out.println(product.displayProducts());

        productIDLabel.setText("Product ID "+product.getProductId());
        nameLabel.setText(("Name "+product.getProductName()));
        categoryLabel.setText("Category "+product.getProductType());
        availableItemLabel.setText(("Available item "+product.getNumberofavailableitems()));

        switch (product.getProductType()){
            case "Electronic":
                otherLabel1.setText("Brand -" + product.getBrand());
                otherLabel2.setText("Warranty Period" + product.getWarrantyPeriod());
                break;
            case "Clothing":
                otherLabel1.setText("size-" + product.getSize());
                otherLabel2.setText("Colour" + product.getColour());
                break;
        }

    }

    public static ArrayList<Product> getProductList(ArrayList<Product> productList, String dropdownOption){
        ArrayList<Product> selectedProductList = new ArrayList<>();
        switch (dropdownOption){
            case "All":
                selectedProductList =productList;
                break;
            case "Electronic":
                for (Product product: productList){
                    if(product.getProductType().equals("Electronic")){
                        selectedProductList.add(product);
                    }
                }
                break;
            case "Clothing":
                for (Product product: productList) {
                    if (product.getProductType().equals("Clothing")) {
                        selectedProductList.add(product);
                    }
                }
                break;
        }
        return selectedProductList;
    }
}
