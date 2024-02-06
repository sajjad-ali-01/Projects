package Pharmacist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

public class OutofStock extends JFrame {
    private JTable Out_Stock;
    JFrame frame;
    public OutofStock() {
        ShowGUI();
    }

    private void Out_Stock() {
        try {
            Connection con = Connect();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM out_of_stock");
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

            Out_Stock.setModel(model);

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
        JFrame frame = new JFrame("Out of stock Medicines");

        JLabel label = new JLabel("Out of Stock Medicines", SwingConstants.CENTER);
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 28));
        label.setBounds(0, 10, 750, 30);

        // Create JTable
        Out_Stock = new JTable();
        Out_Stock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one row selection
        JScrollPane scrollPane = new JScrollPane(Out_Stock);
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

        // Create and add a Search button to search for a bill
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(210, 410, 80, 30);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle searching for a bill
                searchbill(search.getText());
            }
        });
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        panel.add(button);
        panel.add(search);
        panel.add(searchButton);

        frame.add(panel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Out_Stock();
    }
   


    private void searchbill(String searchText) {
        DefaultTableModel model = (DefaultTableModel) Out_Stock.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        Out_Stock.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
    }
    public static void main(String[] args) {
        new OutofStock();
    }
}
