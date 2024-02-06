package Pharmacist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Expiry extends JFrame {
    private JTable Expired_Stock;
    JFrame frame;
    public Expiry() {
        ShowGUI();
    }

    private void Expired_Stock() {
        try {
            Connection con = Connect();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM expire");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Medicine ID");
            model.addColumn("Name");
            model.addColumn("Expiry Date");

            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString("MedicineID"),
                        resultSet.getString("Name"),
                        resultSet.getString("ExpiryDate"),
                });
            }

            Expired_Stock.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private Connection Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "");
            return connection;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    private void ShowGUI() {
        JFrame frame = new JFrame("Expiry Medicines");

        JLabel label = new JLabel("Expired Items", SwingConstants.CENTER);
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 28));
        label.setBounds(0, 10, 750, 30);

        // Create JTable
        Expired_Stock = new JTable();
        Expired_Stock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one row selection
        JScrollPane scrollPane = new JScrollPane(Expired_Stock);
        scrollPane.setBounds(50, 100, 700, 300);

        JPanel panel = new JPanel(null);
        panel.add(scrollPane);
        panel.add(label);

        JSeparator separate = new JSeparator();
        separate.setBounds(0, 45, 800, 25);
        panel.add(separate);
        JButton button=new JButton("Close");
        button.setBounds(0, 60, 800, 40);

        JTextField search = new JTextField();
        search.setBounds(50, 410, 150, 30);

        JButton deleteButton = new JButton("Delete Selected Row");
        deleteButton.setBounds(300, 410, 150, 30);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRow();
            }
        });

        // Create and add a Search button to search for a bill
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(210, 410, 80, 30);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle searching for a bill
                ExpirySearch(search.getText());
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        panel.add(deleteButton);
        panel.add(button);
        panel.add(search);
        panel.add(searchButton);

        frame.add(panel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Expired_Stock();
    }
    private void ExpirySearch(String searchText) {
        DefaultTableModel model = (DefaultTableModel) Expired_Stock.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        Expired_Stock.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
    }
    private void deleteSelectedRow() {
        int selectedRow = Expired_Stock.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;
        }

        try {
            Connection con = Connect();
            Statement statement = con.createStatement();
            DefaultTableModel model = (DefaultTableModel) Expired_Stock.getModel();
            int medicineID = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

            // Delete the selected row from the database
            String deleteQuery = "DELETE FROM expire WHERE MedicineID = " + medicineID;
            statement.executeUpdate(deleteQuery);

            // Remove the selected row from the JTable
            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(null, "Selected row deleted successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public static void main(String[] args) {
        new Expiry();
    }
}
