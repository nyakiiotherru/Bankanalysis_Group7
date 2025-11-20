package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// Importing JDBC classes needed to connect to MariaDB and run SQL queries

public class QueryDemo {
    public static void runSampleQuery() {
        Connection conn = DatabaseConnection.getConnection();

        // If connection failed, stop the method
        if (conn == null) {
            System.out.println(" No database connection available!");
            return;
        }

        try {
            // SQL query to fetch the first 5 rows from the accounts table
            String query = "SELECT * FROM accounts LIMIT 5";
            // Create a Statement object to send SQL commands to the database
            Statement stmt = conn.createStatement();
            // Print a title before listing the sample accounts
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Sample accounts:");
            while (rs.next()) {
                // Read each column value from the current row
                String id = rs.getString("account_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                double balance = rs.getDouble("balance");
                //Print the account information
                System.out.println(id + " | " + firstName + " " + lastName + " | " + balance);
            }
            //close the connection when done
            conn.close();
        } catch (SQLException e) {
            //If any Sql error happens , print message as well as the full error details
            System.out.println(" Query failed!");
            e.printStackTrace();
        }
    }
}

