package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CLASS THAT MAKES THE CONNECTION WITH THE DATABASE
 */

public class ConnectionFactory {

    /**
     * This class contains the name of the driver
     * (initialized through reflection), the database
     * location (DBURL), and the user and the password
     * for accessing the MySQL Server
     */
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://127.0.0.1:3306/schooldb";
    private static final String USER = "root";
    private static final String PASS = "Izabella.08";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * The connection to the DB will be placed in a
     * Singleton* object
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *  aici se conecteaza baza de date cu proiectul Java
     */
    private Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     *  crearea unei noi conexiuni cu baza de date
     */
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    public static void close(Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    /**
     * inchide conectarea statement-ului bazei de date
     * @param statement
     */
    public static void close(Statement statement){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * inchide conectarea ResultSet-ului bazei de date
     * @param resultSet
     */
    public static void close(ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
