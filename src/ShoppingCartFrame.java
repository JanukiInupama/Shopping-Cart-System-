import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ShoppingCartFrame {
    private static JFrame shoppingCartFrame;
    private static JTable shoppingCartTable;
    private static JPanel detailPanel, detailPanel1, detailPanel2;
    private static JLabel totalLabel, finalTotLabel, firstDisLabel, threeItemDisLabel,totalNumberLabel, finalTotNumberLabel, firstDistNumberLabel,threeItemDisNumberLabel;


    public ShoppingCartFrame(){
        shoppingCartFrame = new JFrame("Shopping Cart");
        shoppingCartFrame.setSize(600, 500);
        shoppingCartFrame.setLayout(new GridLayout(2,1));

        shoppingCartTable = createTable(WestminsterFrame.userCart.getProductList());
        JScrollPane shoppingCartPane = new JScrollPane(shoppingCartTable);
        shoppingCartPane.setBorder(new EmptyBorder(28,28,7,28));

        detailPanel =new JPanel(new GridLayout(1, 2));
        detailPanel1 =new JPanel();
        detailPanel2 = new JPanel();

        detailPanel1.setLayout(new BoxLayout(detailPanel1, BoxLayout.Y_AXIS));
        detailPanel2.setLayout(new BoxLayout(detailPanel2, BoxLayout.Y_AXIS));


        totalLabel = new JLabel("Total: ");
        finalTotLabel = new JLabel("Final Total: ");
        firstDisLabel = new JLabel("First Purchase Discount (10%)");
        threeItemDisLabel = new JLabel("Three Item in same Category Discount (20%)");
        totalNumberLabel = new JLabel("88.79$");
        finalTotNumberLabel = new JLabel("60.05$");
        firstDistNumberLabel = new JLabel("8.58$");
        threeItemDisNumberLabel = new JLabel("17.20$");



        detailPanel1.add(totalLabel);
        detailPanel1.add(firstDisLabel);
        detailPanel1.add(threeItemDisLabel);
        detailPanel1.add(finalTotLabel);

        detailPanel2.add(totalNumberLabel);
        detailPanel2.add(firstDistNumberLabel);
        detailPanel2.add(threeItemDisNumberLabel);
        detailPanel2.add(finalTotNumberLabel);


        detailPanel.add(detailPanel1);
        detailPanel.add(detailPanel2);

        shoppingCartFrame.add(shoppingCartPane);
        shoppingCartTable.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 1) { // Check if the edited column is the Quantity column
                DefaultTableModel model1 = (DefaultTableModel) shoppingCartTable.getModel();
                Object quantity =  model1.getValueAt(row, column);
                System.out.println(quantity.toString());
                // Get the relative Product object and update the quantity using the setter method
                Product product = WestminsterFrame.userCart.getProductList().get(row);
                product.setNumberOfItems(Integer.parseInt(quantity.toString()));
                updateTableModel();
                updateInformation();
            }
        });

        shoppingCartFrame.add(detailPanel);
        shoppingCartFrame.setVisible(true);

    }
    public static JTable createTable(ArrayList<Product> productsList) {
        String[] columnNames = {"Product", "Quantity", "Price $"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Product product : productsList) {
            switch (product.getProductType()) {
                case "Electronic":
                    Object[] rowDataElectronic = {product.getProductId()+" "+ product.getProductName()+" "+ product.getBrand()+", "+ product.getWarrantyPeriod()+" week Warranty", product.getNumberofavailableitems(), product.getPrice()};
                    model.addRow(rowDataElectronic);
                    break;
                case "Clothing":
                    Object[] rowDataClothing = {product.getProductId()+" "+ product.getProductName()+", "+ product.getSize()+", "+ product.getColour(), product.getNumberofavailableitems(), product.getPrice()};
                    model.addRow(rowDataClothing);
                    break;
            }
        }
        JTable table = new JTable(model);
        table.setDefaultEditor(Object.class,null);
        return table;
    }

    public static void updateTableModel(){
        DefaultTableModel model = (DefaultTableModel) shoppingCartTable.getModel();
        model.setRowCount(0);
        ArrayList<Product> productList = WestminsterFrame.userCart.getProductList();

        for (Product product : productList) {
            switch (product.getProductType()) {
                case "Electronic":
                    Object[] rowDataElectronic = {product.getProductId(), product.getProductName(), product.getBrand(), product.getWarrantyPeriod(), product.getNumberofavailableitems(), product.getPrice()};
                    model.addRow(rowDataElectronic);
                    break;
                case "Clothing":
                    Object[] rowDataClothing = {product.getProductId(), product.getProductName(), product.getSize(), product.getColour(), product.getNumberofavailableitems(), product.getPrice()};
                    model.addRow(rowDataClothing);
                    break;
            }
        }
    }

    public static void updateInformation(){
        int electronicItemCount = WestminsterFrame.getProductList(WestminsterFrame.userCart.getProductList(),"Electronic").size();
        int clothingItemCount = WestminsterFrame.getProductList(WestminsterFrame.userCart.getProductList(),"Clothing").size();

        firstDistNumberLabel.setText("");

        if(electronicItemCount >= 3 || clothingItemCount >= 3){
            threeItemDisNumberLabel.setText(Double.toString(WestminsterFrame.userCart.threeItemDiscount()));
        }else {
            threeItemDisNumberLabel.setText("0");
        }
        firstDistNumberLabel.setText(Double.toString(WestminsterFrame.userCart.finalTotValue()));

    }
}
