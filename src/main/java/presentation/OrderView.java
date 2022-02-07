package presentation;

import connection.ConnectionFactory;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Bill;
import model.Order;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * CLASS WHICH GENERATES THE ORDER FRAME
 */
public class OrderView extends JFrame{

    JButton backButton;
    JButton addOrder;
    JButton generateBill;
    JLabel productLabel;
    JLabel clientLabel;
    JLabel quantityLabel;
    JTextField productText;
    JTextField clientText;
    JTextField quantityText;
    JLabel idLabel;
    JTextField idText;
    JLabel priceLabel;
    JTextField priceText;
    JTable ordersTable;
    Connection connection;
    JButton viewOrders;
    DefaultTableModel tableModel;

    public OrderView(){

        connection = ConnectionFactory.getConnection();
        final OrderDAO o = new OrderDAO();
        final ProductDAO pr = new ProductDAO();

        this.setSize(1180, 550);
        this.setTitle("ORDER MANAGEMENT");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(2, 200, 220));

        JLabel title = new JLabel("Orders:");
        title.setBounds(430, 10, 400, 70);
        title.setForeground(new Color(0, 0, 0));
        title.setBackground(new Color(2, 200, 220));
        title.setFont(new Font("Arial", Font.BOLD, 45));
        title.setVisible(true);
        this.add(title);

        /**
         * BUTTON FOR LISTING ALL ORDERS IN A JTable
         */
        viewOrders=new JButton("View orders");
        viewOrders.setBounds(870,100,250,60);
        viewOrders.setBackground(new Color(0, 0 ,0));
        viewOrders.setFont(new Font("Arial",Font.BOLD,25));
        viewOrders.setForeground(Color.WHITE);
        viewOrders.setFocusable(false);
        viewOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == viewOrders){
                    for (Order or:o.listAllOrders()) {
                        String addId = String.valueOf(or.getOrderId());
                        String addQu = String.valueOf(or.getQuantity());
                        String addClName = String.valueOf(or.getClientName());
                        String addPrName = String.valueOf(or.getProductName());
                        String addPrice = String.valueOf(or.getPrice());
                        tableModel.addRow(new Object[]{addId, addQu, addClName, addPrName, addPrice});
                    }
                }
            }
        });
        this.add(viewOrders);

        /**
         * BUTTON FOR ADDING A NEW ORDER
         */
        addOrder = new JButton("Add order");
        addOrder.setBounds(870,210,250,60);
        addOrder.setBackground(new Color(0, 0, 0));
        addOrder.setFont(new Font("Arial",Font.BOLD,25));
        addOrder.setForeground(Color.WHITE);
        addOrder.setFocusable(false);
        addOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addOrder){
                    String productNameAdd = productText.getText();
                    Integer quantityAdd = Integer.parseInt(quantityText.getText());
                    Product product = pr.findByName(productNameAdd);
                    if(product == null){
                        JOptionPane.showMessageDialog(null, "NON-EXISTENT PRODUCT!");
                    }
                    if(product.getProductQuantity() < quantityAdd){
                        JOptionPane.showMessageDialog(null, "UNAVAILABLE STOCK!");
                    }else{
                        product.setProductQuantity(product.getProductQuantity() - quantityAdd);
                    }
                    Order insertAnOrder = new Order(Integer.parseInt(quantityText.getText()), clientText.getText(), productText.getText(), Integer.parseInt(priceText.getText()));
                    o.insertOrder(insertAnOrder);
                }
            }
        });
        this.add(addOrder);

        /**
         * BUTTON FOR CREATING A BILL
         */
        generateBill = new JButton("Create bill");
        generateBill.setBounds(870,320,250,60);
        generateBill.setBackground(new Color(0, 0, 0));
        generateBill.setFont(new Font("Arial",Font.BOLD,25));
        generateBill.setForeground(Color.WHITE);
        generateBill.setFocusable(false);
        generateBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == generateBill){
                    Order generateBillOrder = o.findById(Integer.parseInt(idText.getText()));
                    Bill newBill = new Bill();
                    newBill.generateBill(generateBillOrder);
                }
            }
        });
        this.add(generateBill);

        /**
         * JTextFields
         */
        productLabel=new JLabel("Product:");
        productLabel.setBounds(10,90,100,40);
        productLabel.setFont(new Font("Aerial",Font.BOLD,25));
        productLabel.setForeground(Color.BLACK);
        this.add(productLabel);
        productText=new JTextField();
        productText.setBounds(50,130,180,30);
        this.add(productText);

        clientLabel=new JLabel("Client:");
        clientLabel.setBounds(10,170,200,40);
        clientLabel.setFont(new Font("Aerial",Font.BOLD,25));
        clientLabel.setForeground(Color.BLACK);
        this.add(clientLabel);
        clientText=new JTextField();
        clientText.setBounds(50,210,180,30);
        this.add(clientText);

        quantityLabel=new JLabel("Quantity:");
        quantityLabel.setBounds(10,250,200,40);
        quantityLabel.setFont(new Font("Aerial",Font.BOLD,25));
        quantityLabel.setForeground(Color.BLACK);
        this.add(quantityLabel);
        quantityText=new JTextField();
        quantityText.setBounds(50,290,180,30);
        this.add(quantityText);

        idLabel=new JLabel("ID:");
        idLabel.setBounds(10,330,200,40);
        idLabel.setFont(new Font("Aerial",Font.BOLD,25));
        idLabel.setForeground(Color.BLACK);
        this.add(idLabel);
        idText=new JTextField();
        idText.setBounds(50,370,180,30);
        this.add(idText);

        priceLabel=new JLabel("Price:");
        priceLabel.setBounds(10,410,200,40);
        priceLabel.setFont(new Font("Aerial",Font.BOLD,25));
        priceLabel.setForeground(Color.BLACK);
        this.add(priceLabel);
        priceText=new JTextField();
        priceText.setBounds(50,450,180,30);
        this.add(priceText);

        /**
         * JTable for orders
         */
        // Column Names
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("QUANTITY");
        tableModel.addColumn("CLIENT NAME");
        tableModel.addColumn("PRODUCT NAME");
        tableModel.addColumn("PRICE");

        // Initializing the JTable
        ordersTable = new JTable(tableModel);

        // Initializing the JTable
        ordersTable.setBounds(270, 100, 550, 350);
        ordersTable.setBackground(new Color(0, 0,0));
        ordersTable.setFont(new Font("Aerial",Font.BOLD,15));
        ordersTable.setForeground(Color.WHITE);
        this.add(ordersTable);

        /**
         * BUTTON TO GO BACK TO THE MAIN FRAME
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
