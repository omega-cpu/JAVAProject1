package com.mycompany.porjectwork1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class sales extends javax.swing.JFrame {

    public sales() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Connect();
        update_table();
        loadDrugs();
        loadCustomers();
    }

    Connection con;
    PreparedStatement pst;

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/pharmacy_management?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    "pharmacymanager", "DCITPharmacy102022");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
        }
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbDrug = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtquantity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttotalamount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbCustomer = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 18));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Search ");

        jTextField2.addActionListener(evt -> jTextField2ActionPerformed(evt));
        jTextField2.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "sale_id", "drug", "sale_date", "quantity", "total_amount", "customer"
                }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextField2)))
                                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 36));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sales");

        cbDrug.addActionListener(evt -> cbDrugActionPerformed(evt));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 18));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Drug");

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 18));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date");

        txtquantity.addActionListener(evt -> txtquantityActionPerformed(evt));
        txtquantity.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                txtquantityKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 18));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantity");

        txttotalamount.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 18));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Customer");

        cbCustomer.addActionListener(evt -> cbCustomerActionPerformed(evt));

        jButton1.setText("Save");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jButton2.setText("Update");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        jButton3.setText("Delete");
        jButton3.addActionListener(evt -> jButton3ActionPerformed(evt));

        jButton4.setText("Add Customer");
        jButton4.addActionListener(evt -> jButton4ActionPerformed(evt));

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 18));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Amount");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbDrug, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                                        .addComponent(txtquantity, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbCustomer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txttotalamount, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(55, 55, 55)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(47, 47, 47)
                                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
                                .addGap(85, 85, 85))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel1)
                                .addGap(80, 80, 80)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbDrug, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttotalamount, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {
        String searchText = jTextField2.getText().toLowerCase();
        filterTable(searchText);
    }

    private void filterTable(String searchText) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);

        if (searchText.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();
        int sale_id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());

        String drugName = model.getValueAt(selectedIndex, 1).toString();
        String saleDate = model.getValueAt(selectedIndex, 2).toString();
        int quantity = Integer.parseInt(model.getValueAt(selectedIndex, 3).toString());
        double totalAmount = Double.parseDouble(model.getValueAt(selectedIndex, 4).toString());
        String customerName = model.getValueAt(selectedIndex, 5).toString();

        cbDrug.setSelectedItem(drugName);
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(saleDate);
            dateChooser.setDate(date);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error parsing date: " + e.getMessage());
        }
        txtquantity.setText(String.valueOf(quantity));
        txttotalamount.setText(String.valueOf(totalAmount));
        cbCustomer.setSelectedItem(customerName);

        jButton1.setEnabled(false); // Disable Save button
        jButton2.setEnabled(true);  // Enable Update button
    }

    private void cbDrugActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void txtquantityActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void txtquantityKeyReleased(java.awt.event.KeyEvent evt) {
        calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        try {
            String drug = cbDrug.getSelectedItem().toString();
            int quantity = Integer.parseInt(txtquantity.getText());

            String query = "SELECT price FROM drugs WHERE name = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, drug);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                double price = rs.getDouble("price");
                double totalAmount = price * quantity;
                txttotalamount.setText(String.valueOf(totalAmount));
            } else {
                txttotalamount.setText("");
            }
        } catch (NumberFormatException e) {
            txttotalamount.setText("");
            JOptionPane.showMessageDialog(this, "Error calculating total amount: " + e.getMessage());
        } catch (SQLException e) {
            txttotalamount.setText("");
            JOptionPane.showMessageDialog(this, "Error retrieving drug price: " + e.getMessage());
        }
    }

    private void cbCustomerActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String drug = cbDrug.getSelectedItem().toString();
            java.util.Date date = dateChooser.getDate();
            String saleDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            int quantity = Integer.parseInt(txtquantity.getText());
            double totalAmount = Double.parseDouble(txttotalamount.getText());
            String customer = cbCustomer.getSelectedItem().toString();

            int drugId = getDrugId(drug);
            int customerId = getCustomerId(customer);

            pst = con.prepareStatement("INSERT INTO sales(drug_id, sale_date, quantity, total_amount, customer_id) VALUES(?, ?, ?, ?, ?)");
            pst.setInt(1, drugId);
            pst.setString(2, saleDate);
            pst.setInt(3, quantity);
            pst.setDouble(4, totalAmount);
            pst.setInt(5, customerId);

            pst.executeUpdate();

            // Update the current stock of the drug
            pst = con.prepareStatement("UPDATE drugs SET current_stock = current_stock - ? WHERE drug_id = ?");
            pst.setInt(1, quantity);
            pst.setInt(2, drugId);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data stored");

            clearForm();
            update_table();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error storing data: " + e.getMessage());
        }
    }

    private void clearForm() {
        cbDrug.setSelectedIndex(-1);
        dateChooser.setDate(null);
        txtquantity.setText("");
        txttotalamount.setText("");
        cbCustomer.setSelectedIndex(-1);

        jButton1.setEnabled(true);  // Enable Save button
        jButton2.setEnabled(false); // Disable Update button
    }

    private int getDrugId(String drug) throws SQLException {
        String query = "SELECT drug_id FROM drugs WHERE name = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, drug);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("drug_id");
        } else {
            throw new SQLException("Drug ID not found for drug: " + drug);
        }
    }

    private int getCustomerId(String customer) throws SQLException {
        String query = "SELECT customer_id FROM customers WHERE name = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, customer);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("customer_id");
        } else {
            throw new SQLException("Customer ID not found for customer: " + customer);
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int selectedIndex = jTable1.getSelectedRow();
            int saleId = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());

            String drug = cbDrug.getSelectedItem().toString();
            java.util.Date date = dateChooser.getDate();
            String saleDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            int quantity = Integer.parseInt(txtquantity.getText());
            double totalAmount = Double.parseDouble(txttotalamount.getText());
            String customer = cbCustomer.getSelectedItem().toString();

            int drugId = getDrugId(drug);
            int customerId = getCustomerId(customer);

            pst = con.prepareStatement("UPDATE sales SET drug_id=?, sale_date=?, quantity=?, total_amount=?, customer_id=? WHERE sale_id=?");
            pst.setInt(1, drugId);
            pst.setString(2, saleDate);
            pst.setInt(3, quantity);
            pst.setDouble(4, totalAmount);
            pst.setInt(5, customerId);
            pst.setInt(6, saleId);

            pst.executeUpdate();

            // Update the current stock of the drug
            int previousQuantity = Integer.parseInt(model.getValueAt(selectedIndex, 3).toString());
            int difference = quantity - previousQuantity;
            pst = con.prepareStatement("UPDATE drugs SET current_stock = current_stock - ? WHERE drug_id = ?");
            pst.setInt(1, difference);
            pst.setInt(2, drugId);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data updated successfully");

            clearForm();
            update_table();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating data: " + e.getMessage());
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int selectedIndex = jTable1.getSelectedRow();

            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
                return;
            }

            int saleId = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
            int quantity = Integer.parseInt(model.getValueAt(selectedIndex, 3).toString());
            String drug = model.getValueAt(selectedIndex, 1).toString();
            int drugId = getDrugId(drug);

            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                pst = con.prepareStatement("DELETE FROM sales WHERE sale_id = ?");
                pst.setInt(1, saleId);
                pst.executeUpdate();

                // Update the current stock of the drug
                pst = con.prepareStatement("UPDATE drugs SET current_stock = current_stock + ? WHERE drug_id = ?");
                pst.setInt(1, quantity);
                pst.setInt(2, drugId);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data deleted successfully");

                clearForm();
                update_table();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting data: " + e.getMessage());
        }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        String customerName = JOptionPane.showInputDialog(this, "Enter new customer name:");
        if (customerName != null && !customerName.trim().isEmpty()) {
            try {
                pst = con.prepareStatement("INSERT INTO customers(name) VALUES(?)");
                pst.setString(1, customerName);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Customer added successfully");
                loadCustomers();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error adding customer: " + e.getMessage());
            }
        }
    }

    private void update_table() {
        try {
            pst = con.prepareStatement("SELECT s.sale_id, d.name AS drug, s.sale_date, s.quantity, s.total_amount, c.name AS customer FROM sales s JOIN drugs d ON s.drug_id = d.drug_id JOIN customers c ON s.customer_id = c.customer_id");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cc = rsmd.getColumnCount();

            DefaultTableModel dft = (DefaultTableModel) jTable1.getModel();
            dft.setRowCount(0);

            while (rs.next()) {
                Vector<Object> v2 = new Vector<>();
                for (int ii = 1; ii <= cc; ii++) {
                    v2.add(rs.getInt("sale_id"));
                    v2.add(rs.getString("drug"));
                    v2.add(rs.getDate("sale_date"));
                    v2.add(rs.getInt("quantity"));
                    v2.add(rs.getDouble("total_amount"));
                    v2.add(rs.getString("customer"));
                }
                dft.addRow(v2);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating table: " + e.getMessage());
        }
    }

    private void loadDrugs() {
        try {
            pst = con.prepareStatement("SELECT name FROM drugs");
            ResultSet rs = pst.executeQuery();
            cbDrug.removeAllItems();
            while (rs.next()) {
                cbDrug.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading drugs: " + e.getMessage());
        }
    }

    private void loadCustomers() {
        try {
            pst = con.prepareStatement("SELECT name FROM customers");
            ResultSet rs = pst.executeQuery();
            cbCustomer.removeAllItems();
            while (rs.next()) {
                cbCustomer.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading customers: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new sales().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JComboBox<String> cbCustomer;
    private javax.swing.JComboBox<String> cbDrug;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txtquantity;
    private javax.swing.JTextField txttotalamount;
    // End of variables declaration
}
