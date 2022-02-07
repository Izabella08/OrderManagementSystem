package dao;

import connection.ConnectionFactory;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EXTENDS THE CLASS AbstractDAO AND IMPLEMENTS
 *  ALL IT'S METHODS AND ADDS SOME NEW METHODS
 *  FOR ACCESSING THE ORDER TABLE FROM THE DB
 */

public class OrderDAO extends AbstractDAO<Order>{

    protected final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private final String findStatementString = "SELECT * FROM order_table WHERE idorder = ?";
    private static final String insertOrder = "INSERT INTO `order_table` (idorder,client_name,product_name,quantity,price)" + "VALUES (?,?,?,?,?)";

    /**
     * METHOD TO FIND BY ID AN ORDER
     * @param idComanda
     * @return
     */
    public Order findById(int idComanda) {
        Order comanda = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet result = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, idComanda);
            result = findStatement.executeQuery();
            result.next();
            String clName = result.getString("client_name");
            String prName = result.getString("product_name");
            int qu = result.getInt("quantity");
            int price = result.getInt("price");
            comanda = new Order(qu,clName,prName,price);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO: findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(result);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return comanda;
    }

    /**
     * ADD A NEW ORDER METHOD
     * @param o
     * @return
     */
    public int insertOrder(Order o) {
        Connection connection=ConnectionFactory.getConnection();
        PreparedStatement statement =null;
        Random randomNr = new Random();
        int idOrderInsert = randomNr.nextInt(100);
        try {
            statement = connection.prepareStatement(insertOrder,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idOrderInsert);
            statement.setString(2, o.getClientName());
            statement.setString(3, o.getProductName());
            statement.setInt(4, o.getQuantity());
            statement.setInt(5,o.getPrice());
            statement.execute();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING ,"ORDER_DATABASE_OPERATIONS insert " + e.getMessage());
        }
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
        return idOrderInsert;
    }

    /**
     * LIST ALL ODERS METHOD
     * @return
     */
    public List<Order> listAllOrders(){
        List<Order> ordersList = new ArrayList<Order>();
        Order o = null;
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM `order_table`");
            result = statement.executeQuery();
            while (result.next()) {
                int idOrder = result.getInt("idorder");
                String clientName = result.getString("client_name");
                String productName = result.getString("product_name");
                int quantity = result.getInt("quantity");
                int price = result.getInt("price");
                o = new Order(quantity,clientName, productName);
                o.setOrderId(idOrder);
                o.setPrice(price);
                ordersList.add(o);
            }
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ORDER_DATABASE_OPERATIONS:ListAll " + e.getMessage());
        }
        ConnectionFactory.close(result);
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
        return ordersList;
    }

}
