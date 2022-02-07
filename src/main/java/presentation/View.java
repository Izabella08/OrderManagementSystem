package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * CLASS WHICH PRODUCES THE MAIN FRAME
 */

public class View {

    JFrame frame;
    JPanel panel;
    JButton client;
    JButton product;
    JButton order;

    public View() {
        /**
         * CREATING THE FRAME
         */
        frame=new JFrame();
        frame.setSize(800, 500);
        frame.setTitle("ORDER MANAGEMENT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(2, 200, 220));

        JLabel title = new JLabel("WELCOME!");
        title.setBounds(250, 10, 300, 70);
        title.setForeground(new Color(0, 0, 0));
        title.setBackground(new Color(2, 200, 220));
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setVisible(true);
        frame.add(title);

        panel = new JPanel();
        panel.setSize(900,300);
        panel.setBounds(0,0,800,500);
        panel.setBackground(new Color(2, 200, 220));
        panel.setLayout(null);
        panel.setVisible(true);
        frame.add(panel);

        /**
         * CLIENT BUTTON TO GO TO THE CLIENT FRAME
         */
        client = new JButton("CLIENTS");
        client.setBounds(80,100,300,70);
        client.setBackground(new Color(0, 0, 0));
        client.setFont(new Font("Arial",Font.BOLD,30));
        client.setForeground(Color.WHITE);
        client.setFocusable(false);
        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==client)
                {
                    frame.dispose();
                    try {
                        ClientView clFrame = new ClientView();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    } catch (IntrospectionException introspectionException) {
                        introspectionException.printStackTrace();
                    } catch (InvocationTargetException invocationTargetException) {
                        invocationTargetException.printStackTrace();
                    }
                }
            }
        });
        panel.add(client);

        /**
         * PRODUCTS BUTTON TO GO TO THE PRODUCTS FRAME
         */
        product=new JButton("PRODUCTS");
        product.setBounds(220,210,300,70);
        product.setBackground(new Color(0, 0, 0));
        product.setFont(new Font("Arial",Font.BOLD,30));
        product.setForeground(Color.WHITE);
        product.setFocusable(false);
        product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==product)
                {
                    frame.dispose();
                    try {
                        ProductView prFrame = new ProductView();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    } catch (IntrospectionException introspectionException) {
                        introspectionException.printStackTrace();
                    } catch (InvocationTargetException invocationTargetException) {
                        invocationTargetException.printStackTrace();
                    }
                }
            }
        });
        panel.add(product);

        /**
         * ORDERS BUTTON TO GO TO THE ORDERS FRAME
         */
        order = new JButton("ORDERS");
        order.setBounds(360,320,300,70);
        order.setBackground(new Color(0, 0, 0));
        order.setFont(new Font("Arial",Font.BOLD,30));
        order.setForeground(Color.WHITE);
        order.setFocusable(false);
        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==order)
                {
                    frame.dispose();
                    OrderView orFrame = new OrderView();
                }
            }
        });
        panel.add(order);

        frame.setVisible(true);
    }
}
