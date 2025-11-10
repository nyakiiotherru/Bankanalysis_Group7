package org.example;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.sql.*;

public class TopAccountsChart extends JFrame {

    public TopAccountsChart() {
        setTitle("Top Accounts by Transaction Volume");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT a.account_id, CONCAT(a.first_name, ' ', a.last_name) AS name, SUM(t.amount) AS total_amount " +
                             "FROM accounts a " +
                             "JOIN transactions t ON a.account_id = t.account_id " +
                             "GROUP BY a.account_id, a.first_name, a.last_name " +
                             "ORDER BY total_amount DESC " +
                             "LIMIT 10")) {

            while (rs.next()) {
                dataset.addValue(rs.getDouble("total_amount"), "Accounts", rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Top 10 Accounts by Transaction Volume",
                "Account",
                "Total Amount",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TopAccountsChart frame = new TopAccountsChart();
            frame.setVisible(true);
        });
    }
}
