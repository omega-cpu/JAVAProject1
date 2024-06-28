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
import java.util.Vector;
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

public class ReportViewer extends JDialog {
    
    private Connection con;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    private JTable reportTable;
    private DefaultTableModel tableModel;

    public ReportViewer(Connection con) {
        this.con = con;
        setTitle("Reports");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

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
                viewSalesReport();
            }
        });
        panel.add(viewSalesButton);

        JButton viewPurchaseButton = new JButton("View Purchase Report");
        viewPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPurchaseReport();
            }
        });
        panel.add(viewPurchaseButton);

        getContentPane().add(panel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        reportTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

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

            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(rsmd.getColumnName(i));
            }

            double totalAmount = 0;

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                tableModel.addRow(row);
                totalAmount += rs.getDouble("total_amount");
            }

            JOptionPane.showMessageDialog(this, "Total Sales Amount: " + totalAmount);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving sales report: " + e.getMessage());
        }
    }

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

            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(rsmd.getColumnName(i));
            }

            double totalAmount = 0;

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                tableModel.addRow(row);
                totalAmount += rs.getDouble("total_amount");
            }

            JOptionPane.showMessageDialog(this, "Total Purchase Amount: " + totalAmount);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving purchase report: " + e.getMessage());
        }
    }
}
