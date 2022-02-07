package model;

import model.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CLASS THAT GENERATES A BILL IN A .txt file
 */

public class Bill {
    public void generateBill(Order order){
        try{
            File myFile = new File("bill.txt");
            FileWriter myWriter = new FileWriter("bill.txt");
            int totalPrice = order.getPrice() * order.getQuantity();
            myWriter.write("CLIENT NAME: "+order.getClientName()+"\n" +"PRODUCT NAME: "+order.getProductName()+"\n" + "TOTAL: "+totalPrice);
            myWriter.close();
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            }
            else {
                System.out.println("Bill created succesfully!");
            }
            } catch (IOException e) {
                System.out.println("An error occurred while processing the bill!");
                e.printStackTrace();
            }
    }
}
