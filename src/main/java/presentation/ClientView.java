package presentation;

import connection.ConnectionFactory;
import dao.ClientDAO;
import model.Client;
import model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * CLASS WHICH GENERATES THE CLIENT FRAME
 */

public class ClientView extends JFrame {

    JButton addClient;
    JButton editClient;
    JButton deleteClient;
    JButton viewClients;
    JLabel nameLabel;
    JLabel adressLabel;
    JLabel emailLabel;
    JTextField nameText;
    JTextField adressText;
    JTextField emailText;
    JButton backButton;
    JTable clientsTable;
    Connection connection;
    DefaultTableModel tableModel;
    JLabel idLabel;
    JTextField idText;

    public ClientView() throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        connection = ConnectionFactory.getConnection();
        final ClientDAO cl = new ClientDAO();

        this.setSize(1180, 550);
        this.setTitle("ORDER MANAGEMENT");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(2, 200, 220));

        JLabel title = new JLabel("CLIENTS:");
        title.setBounds(380, 10, 300, 70);
        title.setForeground(new Color(0, 0, 0));
        title.setBackground(new Color(2, 200, 220));
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setVisible(true);
        this.add(title);

        /**
         * BUTTON FOR ADDING A NEW CLIENT
         */
        addClient = new JButton("Add new client");
        addClient.setBounds(870,90,250,60);
        addClient.setBackground(new Color(0, 0, 0));
        addClient.setFont(new Font("Arial",Font.BOLD,25));
        addClient.setForeground(Color.WHITE);
        addClient.setFocusable(false);
        addClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==addClient){
                    Client newClient = new Client(nameText.getText(), adressText.getText(), emailText.getText());
                    cl.insertClient(newClient);
                }
            }
        });
        this.add(addClient);

        /**
         * BUTTON FOR EDITING A CLIENT'S PROFILE
         */
        editClient = new JButton("Edit profile");
        editClient.setBounds(870,200,250,60);
        editClient.setBackground(new Color(0, 0, 0));
        editClient.setFont(new Font("Arial",Font.BOLD,25));
        editClient.setForeground(Color.WHITE);
        editClient.setFocusable(false);
        editClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == editClient){
                   //Client editAClient = new Client(Integer.parseInt(idText.getToolTipText()),nameText.getText(), adressText.getText(), emailText.getText());
                    cl.updateClient(Integer.parseInt(idText.getText()), nameText.getText(), adressText.getText(), emailText.getText());
                }
            }
        });
        this.add(editClient);

        /**
         * BUTTON FOR DELETING A CLIENT'S PROFILE
         */
        deleteClient = new JButton("Delete profile");
        deleteClient.setBounds(870,310,250,60);
        deleteClient.setBackground(new Color(0, 0, 0));
        deleteClient.setFont(new Font("Arial",Font.BOLD,25));
        deleteClient.setForeground(Color.WHITE);
        deleteClient.setFocusable(false);
        deleteClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == deleteClient){
                    cl.deleteClient(Integer.parseInt(idText.getText()));
                }
            }
        });
        this.add(deleteClient);

        /**
         * BUTTON FOR LISTING ALL THE CLIENTS IN A JTable
         */
        viewClients=new JButton("View all clients");
        viewClients.setBounds(870,420,250,60);
        viewClients.setBackground(new Color(0, 0 ,0));
        viewClients.setFont(new Font("Arial",Font.BOLD,25));
        viewClients.setForeground(Color.WHITE);
        viewClients.setFocusable(false);
        viewClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == viewClients){
                    for (Client c:cl.listAllClients()) {
                        String  addId = String.valueOf(c.getClientId());
                        String  addName = String.valueOf(c.getClientName());
                        String  addAddr = String.valueOf(c.getClientAddress());
                        String addEmail = String.valueOf(c.getEmailAddress());
                        tableModel.addRow(new Object[]{addId, addName, addAddr, addEmail});
                    }
                }
            }
        });
        this.add(viewClients);

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

        adressLabel=new JLabel("Address:");
        adressLabel.setBounds(10,170,200,40);
        adressLabel.setFont(new Font("Aerial",Font.BOLD,30));
        adressLabel.setForeground(Color.BLACK);
        this.add(adressLabel);
        adressText=new JTextField();
        adressText.setBounds(50,210,180,30);
        this.add(adressText);

        emailLabel=new JLabel("Email:");
        emailLabel.setBounds(10,250,200,40);
        emailLabel.setFont(new Font("Aerial",Font.BOLD,30));
        emailLabel.setForeground(Color.BLACK);
        this.add(emailLabel);
        emailText=new JTextField();
        emailText.setBounds(50,290,180,30);
        this.add(emailText);

        idLabel=new JLabel("ID:");
        idLabel.setBounds(10,330,200,40);
        idLabel.setFont(new Font("Aerial",Font.BOLD,30));
        idLabel.setForeground(Color.BLACK);
        this.add(idLabel);
        idText=new JTextField();
        idText.setBounds(50,370,180,30);
        this.add(idText);

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

        /**
         * JTable for clients
         */
        // Column Names
       // tableModel = new DefaultTableModel();
        tableModel = cl.fields(cl, cl.listAllClients());
        tableModel.addColumn("ID");
        tableModel.addColumn("NAME");
        tableModel.addColumn("ADDRESS");
        tableModel.addColumn("EMAIL");

        // Initializing the JTable
        clientsTable = new JTable(tableModel);

        clientsTable.setBounds(270, 100, 550, 350);
        clientsTable.setBackground(new Color(0, 0,0));
        clientsTable.setFont(new Font("Aerial",Font.BOLD,15));
        clientsTable.setForeground(Color.WHITE);
        this.add(clientsTable);

        this.setVisible(true);
    }


}
