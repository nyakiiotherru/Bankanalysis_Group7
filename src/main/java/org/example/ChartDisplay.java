package org.example;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ChartDisplay extends JFrame {

    public ChartDisplay() {
        setTitle("Transaction Amount Histogram");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        double[] amounts = getTransactionAmounts();
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Transaction Amounts", amounts, 10);

        JFreeChart chart = ChartFactory.createHistogram(
                "Transaction Amounts Distribution",
                "Amount Range",
                "Frequency",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private double[] getTransactionAmounts() {
        ArrayList<Double> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT amount FROM transactions")) {

            while (rs.next()) {
                list.add(rs.getDouble("amount"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        double[] array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChartDisplay frame = new ChartDisplay();
            frame.setVisible(true);
        });
    }
}
