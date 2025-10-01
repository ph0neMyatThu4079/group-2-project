package com.napier.proj.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static Connection conn;
    
    private static String ip = "db";
    private static String port = "3306";
    private static String dbname = "world";
    private static String username = "root";
    private static String password = "toor";

    public static void openConnection() {
        int retries = 10;
        for (int i = 0; i < retries; i++) {

            System.out.println("Connecting to database...");
            try {
                if(conn == null || conn.isClosed()){
                    Thread.sleep(5000);
                    conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbname +
                            "?useSSL=false&allowPublicKeyRetrieval=true", username, password);
                    System.out.println("Connected to database successfully");
                    break; // stop retrying once connected
                }
            } catch (SQLException e) {
                System.out.println("Failed to connect to database " + Integer.toString(i));
                System.out.println(e.getMessage());

            } catch (InterruptedException e){
                System.out.println("Thread interrupted");
            }
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void closeConnection() {
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

}
