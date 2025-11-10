package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryDemo {
    public static void runSampleQuery() {
        Connection conn = DatabaseConnection.getConnection();

        if (conn == null) {
            System.out.println(" No database connection available!");
            return;
        }

        try {
            String query = "SELECT * FROM accounts LIMIT 5";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Sample accounts:");
            while (rs.next()) {
                String id = rs.getString("account_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                double balance = rs.getDouble("balance");

                System.out.println(id + " | " + firstName + " " + lastName + " | " + balance);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(" Query failed!");
            e.printStackTrace();
        }
    }
}

