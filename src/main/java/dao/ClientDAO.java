package dao;

import connection.ConnectionFactory;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EXTENDS THE CLASS AbstractDAO AND IMPLEMENTS
 * ALL IT'S METHODS AND ADDS SOME NEW METHODS
 * FOR ACCESSING THE CLIENT TABLE FROM THE DB
 */

public class ClientDAO extends AbstractDAO<Client>{

    protected final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private final String findStatementString = "SELECT * FROM client WHERE idclient = ?";
    private String get = "SELECT * from client";
    private static final String insertClient = "INSERT INTO client (idclient, client_name,client_address, email)" + "VALUES (?,?,?,?)";

    /**
     * FIND BY ID METHOD
     * @param idClient
     * @return
     */
    public Client findById(int idClient) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        Client cl = null;
        ResultSet result = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, idClient);
            result = findStatement.executeQuery();
            result.next();
            String name = result.getString("client_name");
            cl = new Client(name);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO: findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(result);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return cl;
    }

    /**
     * METHOD TO ADD A NEW CLIENT
     * @param c
     * @return
     */
    public int insertClient(Client c) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        Random randomNr = new Random();
        int idClientInsert = randomNr.nextInt(100);
        try {
            statement = connection.prepareStatement(insertClient,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idClientInsert);
            statement.setString(2, c.getClientName());
            statement.setString(3, c.getClientAddress());
            statement.setString(4,c.getEmailAddress());
            statement.executeUpdate();

        }catch(SQLException e){
            LOGGER.log(Level.WARNING, "CLIENT_DATABASE_OPERATIONS insert " + e.getMessage());
        }
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
        return idClientInsert;
    }

    /**
     * DELETING A CLIENT METHOD
     * @param id
     */
    public void deleteClient(int id) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM client WHERE idclient = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CLIENT_DATABASE_OPERATIONS:DELETE " + e.getMessage());
        }
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }

    /**
     * UPDATE A CLIENT'S PROFILE METHOD
     * @param id
     * @param clientName
     * @param clientAddress
     * @param email
     */
    public void updateClient(int id, String clientName, String clientAddress, String email) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE client SET client_name = ? WHERE idclient = ?");
            statement.setString(1, clientName);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE client SET client_address = ? WHERE idclient = ?");
            statement.setString(1, clientAddress);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE client SET email = ? WHERE idclient = ?");
            statement.setString(1, email);
            statement.setInt(2, id);
            statement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PRODUCT_DATABASE_OPERATIONS : EDIT" + e.getMessage());
        }
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }

    /**
     * LIST ALL CLIENTS METHOD
     * @return
     */
    public List<Client> listAllClients(){
        List<Client> clientsList = new ArrayList<Client>();
        Client c = null;
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM client");
            result = statement.executeQuery();
            while (result.next()) {
                int idClient = result.getInt("idclient");
                String clientName = result.getString("client_name");
                String clientAddress = result.getString("client_address");
                String email = result.getString("email");
                c = new Client(clientName, clientAddress);
                c.setClientId(idClient);
                c.setEmailAddress(email);
                clientsList.add(c);
            }
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CLIENT_DATABASE_OPERATIONS: ListAll " + e.getMessage());
        }
        ConnectionFactory.close(result);
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
        return clientsList;
    }

}
