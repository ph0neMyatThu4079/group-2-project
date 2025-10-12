package com.napier.proj.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles the configuration and management of the database connection.
 * <p>
 * This class provides static methods to open, access, and close the connection
 * to the MySQL database used by the application. It includes retry logic to handle
 * temporary connection failures, making it suitable for containerized environments
 * like Docker where the database may take time to start.
 * </p>
 *
 * @author
 *     Phone Myat Thu
 */
public class DatabaseConfig {
    /** The active database connection instance. */
    private static Connection conn;
    
    private static String ip = "db";
    private static String port = "3306";
    private static String dbname = "world";
    private static String username = "root";
    private static String password = "toor";

    /**
     * Opens a connection to the MySQL database.
     * <p>
     * The method attempts to connect multiple times (up to 10 retries),
     * waiting 5 seconds between each attempt. This is useful in cases
     * where the database container or server is not immediately ready.
     * </p>
     */
    public static void openConnection() {
        int retries = 10; // maximum number of retries

        // Attempt to connect multiple times
        for (int i = 0; i < retries; i++) {
            System.out.println("Connecting to database...");
            try {
                // Only attempt connection if no active connection exists
                if(conn == null || conn.isClosed()){
                    // Wait 5 seconds before retrying (gives time for DB to initialize)
                    Thread.sleep(5000);

                    // Establish a connection to the database using JDBC
                    conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbname +
                            "?useSSL=false&allowPublicKeyRetrieval=true", username, password);
                    System.out.println("Connected to database successfully");
                    break; // stop retrying once connected
                }
            } catch (SQLException e) {
                // Handle SQL exceptions (e.g., database not ready or incorrect credentials)
                System.out.println("Failed to connect to database " + Integer.toString(i));
                System.out.println(e.getMessage());

            } catch (InterruptedException e){
                System.out.println("Thread interrupted");
            }
        }
    }

    /**
     * Returns the active database connection instance.
     *
     * @return the current {@link Connection} object, or {@code null} if not connected
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * Closes the active database connection if it exists.
     * <p>
     * Ensures that resources are released properly to prevent memory leaks
     * or connection pool exhaustion.
     * </p>
     */
    public static void closeConnection() {
        if(conn != null){
            try {
                conn.close();// close the connection safely
            } catch (SQLException e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

}
