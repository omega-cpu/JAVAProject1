package com.mycompany.porjectwork1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

/**
 * ReportViewer class handles displaying sales and purchase reports in a dialog.
 */
public class ReportViewer extends JDialog {

    private Connection con;  // Database connection
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    private JTable reportTable;
    private DefaultTableModel tableModel;

    /**
     * Constructor to initialize ReportViewer with database connection.
     */
    public ReportViewer(Connection con) {
        this.con = con;
        setTitle("Reports");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();  // Initialize GUI components
    }

    /**
     * Method to initialize GUI components.
     */
    private void initComponents() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Start Date:"));
        startDateChooser = new JDateChooser();
        panel.add(startDateChooser);

        panel.add(new JLabel("End Date:"));
        endDateChooser = new JDateChooser();
        panel.add(endDateChooser);

        JButton viewSalesButton = new JButton("View Sales Report");
        viewSalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSalesReport();  // Handle view sales report action
            }
        });
        panel.add(viewSalesButton);

        JButton viewPurchaseButton = new JButton("View Purchase Report");
        viewPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPurchaseReport();  // Handle view purchase report action
            }
        });
        panel.add(viewPurchaseButton);

        getContentPane().add(panel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        reportTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Method to view the sales report.
     */
    private void viewSalesReport() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = sdf.format(startDateChooser.getDate());
        String endDate = sdf.format(endDateChooser.getDate());

        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM sales WHERE sale_date BETWEEN ? AND ?");
            pst.setString(1, startDate);
            pst.setString(2, endDate);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);

            // Add column names to the table model
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(rsmd.getColumnName(i));
            }

            double totalAmount = 0;

            // Using a List to store rows of data
            List<List<Object>> rowDataList = new ArrayList<>();
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                rowDataList.add(row);
                totalAmount += rs.getDouble("total_amount");
            }

            // Using an Iterator to add rows to the table model
            Iterator<List<Object>> iterator = rowDataList.iterator();
            while (iterator.hasNext()) {
                tableModel.addRow(iterator.next().toArray());
            }

            JOptionPane.showMessageDialog(this, "Total Sales Amount: " + totalAmount);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving sales report: " + e.getMessage());
        }
    }

    /**
     * Method to view the purchase report.
     */
    private void viewPurchaseReport() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = sdf.format(startDateChooser.getDate());
        String endDate = sdf.format(endDateChooser.getDate());

        try {
            PreparedStatement pst = con.prepareStatement("SELECT purchase_id, drug_id, purchase_date, quantity, total_amount FROM purchase_history WHERE purchase_date BETWEEN ? AND ?");
            pst.setString(1, startDate);
            pst.setString(2, endDate);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);

            // Add column names to the table model
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(rsmd.getColumnName(i));
            }

            double totalAmount = 0;

            // Using a List to store rows of data
            List<List<Object>> rowDataList = new ArrayList<>();
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                rowDataList.add(row);
                totalAmount += rs.getDouble("total_amount");
            }

            // Using an Iterator to add rows to the table model
            Iterator<List<Object>> iterator = rowDataList.iterator();
            while (iterator.hasNext()) {
                tableModel.addRow(iterator.next().toArray());
            }

            JOptionPane.showMessageDialog(this, "Total Purchase Amount: " + totalAmount);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving purchase report: " + e.getMessage());
        }
    }
}
