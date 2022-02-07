package presentation;

import connection.ConnectionFactory;
import dao.ClientDAO;
import dao.ProductDAO;
import model.Client;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

/**
 * CLASS WHICH GENERATES THE PRODUCT FRAME
 */

public class ProductView extends JFrame{

    JButton backButton;
    JButton addProduct;
    JButton editProduct;
    JButton deleteProduct;
    JButton viewProducts;
    JLabel nameLabel;
    JLabel quantityLabel;
    JLabel priceLabel;
    JTextField nameText;
    JTextField quantityText;
    JTextField priceText;
    JTable productsTable;
    Connection connection;
    DefaultTableModel tableModel;
    JLabel idLabel;
    JTextField idText;

    public ProductView() throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        connection = ConnectionFactory.getConnection();
        final ProductDAO p = new ProductDAO();

        this.setSize(1180, 550);
        this.setTitle("ORDER MANAGEMENT");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(2, 200, 220));

        JLabel title = new JLabel("PRODUCTS:");
        title.setBounds(390, 10, 300, 80);
        title.setForeground(new Color(0, 0, 0));
        title.setBackground(new Color(2, 200, 220));
        title.setFont(new Font("Arial", Font.BOLD, 45));
        title.setVisible(true);
        this.add(title);

        /**
         * BUTTON TO ADD A NEW PRODUCT
         */
        addProduct = new JButton("Add new product");
        addProduct.setBounds(870,90,250,60);
        addProduct.setBackground(new Color(0, 0, 0));
        addProduct.setFont(new Font("Arial",Font.BOLD,25));
        addProduct.setForeground(Color.WHITE);
        addProduct.setFocusable(false);
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addProduct){
                    Product newProduct = new Product(nameText.getText(),Integer.parseInt(quantityText.getText()),Integer.parseInt(priceText.getText()));
                    p.insertProduct(newProduct);
                }
            }
        });
        this.add(addProduct);

        /**
         * BUTTON TO EDIT PRODUCT DETAILS
         */
        editProduct = new JButton("Edit product");
        editProduct.setBounds(870,200,250,60);
        editProduct.setBackground(new Color(0, 0, 0));
        editProduct.setFont(new Font("Arial",Font.BOLD,25));
        editProduct.setForeground(Color.WHITE);
        editProduct.setFocusable(false);
        editProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == editProduct){
                    p.updateProduct(Integer.parseInt(idText.getText()),nameText.getText(),Integer.parseInt(quantityText.getText()),Integer.parseInt(priceText.getText()));
                }
            }
        });
        this.add(editProduct);

        /**
         * BUTTON TO DELETE AN EXISTING PRODUCT
         */
        deleteProduct = new JButton("Delete product");
        deleteProduct.setBounds(870,310,250,60);
        deleteProduct.setBackground(new Color(0, 0, 0));
        deleteProduct.setFont(new Font("Arial",Font.BOLD,25));
        deleteProduct.setForeground(Color.WHITE);
        deleteProduct.setFocusable(false);
        deleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==deleteProduct){
                    p.deleteProduct(Integer.parseInt(idText.getText()));
                }
            }
        });
        this.add(deleteProduct);

        /**
         * BUTTON TO LIST ALL THE PRODUCTS IN A JTable
         */
        viewProducts=new JButton("View all products");
        viewProducts.setBounds(870,420,250,60);
        viewProducts.setBackground(new Color(0, 0 ,0));
        viewProducts.setFont(new Font("Arial",Font.BOLD,25));
        viewProducts.setForeground(Color.WHITE);
        viewProducts.setFocusable(false);
        viewProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == viewProducts){
                    for (Product pr:p.listAllProducts()) {
                        String addId = String.valueOf(pr.getProductId());
                        String addName = String.valueOf(pr.getProductName());
                        String addQu = String.valueOf(pr.getProductQuantity());
                        String addPrice = String.valueOf(pr.getProductPrice());
                        tableModel.addRow(new Object[]{addId, addName, addQu, addPrice});
                    }
                }
            }
        });
        this.add(viewProducts);

        /**
         * JTextFields
         */
        nameLabel=new JLabel("Name:");
        nameLabel.setBounds(10,90,100,40);
        nameLabel.setFont(new Font("Aerial",Font.BOLD,30));
        nameLabel.setForeground(Color.BLACK);
        this.add(nameLabel);
        nameText=new JTextField();
        nameText.setBounds(50,130,180,30);
        this.add(nameText);

        quantityLabel=new JLabel("Quantity:");
        quantityLabel.setBounds(10,170,200,40);
        quantityLabel.setFont(new Font("Aerial",Font.BOLD,30));
        quantityLabel.setForeground(Color.BLACK);
        this.add(quantityLabel);
        quantityText=new JTextField();
        quantityText.setBounds(50,210,180,30);
        this.add(quantityText);

        priceLabel=new JLabel("Price:");
        priceLabel.setBounds(10,250,200,40);
        priceLabel.setFont(new Font("Aerial",Font.BOLD,30));
        priceLabel.setForeground(Color.BLACK);
        this.add(priceLabel);
        priceText=new JTextField();
        priceText.setBounds(50,290,180,30);
        this.add(priceText);

        idLabel=new JLabel("ID:");
        idLabel.setBounds(10,330,200,40);
        idLabel.setFont(new Font("Aerial",Font.BOLD,30));
        idLabel.setForeground(Color.BLACK);
        this.add(idLabel);
        idText=new JTextField();
        idText.setBounds(50,370,180,30);
        this.add(idText);

        /**
         * JTable for all products
         */
        // Column Names
        //tableModel = new DefaultTableModel();
        tableModel = p.fields(p, p.listAllProducts());
        tableModel.addColumn("ID");
        tableModel.addColumn("NAME");
        tableModel.addColumn("QUANTITY");
        tableModel.addColumn("PRICE");

        // Initializing the JTable
        productsTable = new JTable(tableModel);

        // Initializing the JTable
        productsTable.setBounds(270, 100, 550, 350);
        productsTable.setBackground(new Color(0, 0,0));
        productsTable.setFont(new Font("Aerial",Font.BOLD,15));
        productsTable.setForeground(Color.WHITE);
        this.add(productsTable);

        /**
         * BACK BUTTON TO GO BACK TO THE MAIN FRAME
         */
        backButton=new JButton("Back");
        backButton.setBounds(930,10,150,30);
        backButton.setBackground(new Color(0, 0,0));
        backButton.setFont(new Font("Aerial", Font.BOLD,20));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusable(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==backButton)
                {
                    dispose();
                    View v=new View();
                }
            }
        });
        this.add(backButton);

        this.setVisible(true);
    }
}
