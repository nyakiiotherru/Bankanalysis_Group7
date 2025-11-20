package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class is responsible for connecting the Java program to the MariaDB database
public class DatabaseConnection {
    //Connection details
    private static final String URL = "jdbc:mariadb://localhost:3306/bank_analysis";
    private static final String USER = "root";
    private static final String PASSWORD = "12/02Dogmatix";

    public static Connection getConnection() {
        try {
            //test to establish a successful connection to the database
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
            return null;
        }
    }
}

