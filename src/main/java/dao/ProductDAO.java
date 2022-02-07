package dao;

import connection.ConnectionFactory;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EXTENDS THE CLASS AbstractDAO AND IMPLEMENTS
 * ALL IT'S METHODS AND ADDS SOME NEW METHODS
 * FOR ACCESSING THE PRODUCT TABLE FROM THE DB
 */

public class ProductDAO extends AbstractDAO<Product>{

    protected final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private final String findStatementString = "SELECT * FROM product WHERE product_name = ?";
    private static final String insertProduct = "INSERT INTO product (idproduct, product_name,product_quantity,product_price)" + "VALUES (?,?,?,?)";

    /**
     * METHOD TO FIND BY ID A PRODUCT
     * @param idProdus
     * @return
     */
    public Product findById(int idProdus) {
        Product produs = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet result = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, idProdus);
            result = findStatement.executeQuery();
            result.next();
            String nume = result.getString("nume");
            int pret = result.getInt("pret");
            int cantitate = result.getInt("cantitate");
            produs = new Product(nume,cantitate,pret);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO: findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(result);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return produs;
    }

    /**
     * METHOD TO FIND BY NAME A PRODUCT
     * @param productName
     * @return
     */
    public Product findByName(String productName) {
        Product produs = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet result = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, productName);
            result = findStatement.executeQuery();
            result.next();
            int price = result.getInt("product_price");
            int qu = result.getInt("product_quantity");
            produs = new Product(productName, qu,price);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO: findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(result);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return produs;
    }

    /**
     * ADD A NEW PRODUCT METHOD
     * @param p
     * @return
     */
    public int insertProduct(Product p) {
        Connection connection=ConnectionFactory.getConnection();
        PreparedStatement statement=null;
        Random randomNr = new Random();
        int idProductInsert=randomNr.nextInt(100);
        try {
            statement = connection.prepareStatement(insertProduct,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idProductInsert);
            statement.setString(2, p.getProductName());
            statement.setInt(3,p.getProductQuantity());
            statement.setFloat(4, p.getProductPrice());
            statement.execute();
        }catch(SQLException e) {
            LOGGER.log(Level.WARNING ,"PRODUCT_DATABASE_OPERATIONS insert "+ e.getMessage());
        }
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);

        return idProductInsert;
    }

    /**
     * DELETE AN EXISTING PRODUCT METHOD
     * @param id
     */
    public void deleteProduct(int id) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM product WHERE idproduct = ?");
            statement.setInt(1, id);
            statement.execute();
        }catch(SQLException e) {
            LOGGER.log(Level.WARNING , "PRODUCT_DATABASE_OPERATIONS:DELETE "+e.getMessage());
        }
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }

    /**
     * EDIT A PRODUCT'S DETAILS METHOD
     * @param id
     * @param productName
     * @param quantity
     * @param price
     */
    public void updateProduct(int id, String productName, int quantity, int price) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE product SET product_name = ? WHERE idproduct = ?");
            statement.setString(1, productName);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE product SET product_quantity = ? WHERE idproduct = ?");
            statement.setInt(1, quantity);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE product SET product_price = ? WHERE idproduct = ?");
            statement.setFloat(1, price);
            statement.setInt(2, id);
            statement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PRODUCT_DATABASE_OPERATIONS : EDIT" + e.getMessage());
        }
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }

    /**
     * LIST ALL PRODUCTS METHOD
     * @return
     */
    public List<Product> listAllProducts(){
        List<Product> productsList = new ArrayList<Product>();
        Product p = null;
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM product");
            result = statement.executeQuery();
            while (result.next()) {
                int idProduct = result.getInt("idproduct");
                String productName = result.getString("product_name");
                int quantity = result.getInt("product_quantity");
                int price = result.getInt("product_price");
                p = new Product(productName, quantity, price);
                p.setProductId(idProduct);
                productsList.add(p);
            }
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CLIENT_DATABASE_OPERATIONS:LISTALL " + e.getMessage());
        }
        ConnectionFactory.close(result);
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
        return productsList;
    }
}
