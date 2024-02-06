package Pharmacist;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

public class bill extends JFrame {
    private JTable billTable;
    private JTable return_Table;
    private JDateChooser dateChooser;
    JFrame f;
    JFrame frame;
    public bill() {
        ShowGUI();
    }

    private void viewbills() {
        try {
            Connection con = Connect();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bill");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("billID");
            model.addColumn("billDate");
            model.addColumn("totalPaid");
            model.addColumn("generatedBy");

            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString("billID"),
                        resultSet.getString("billDate"),
                        resultSet.getString("totalPaid"),
                        resultSet.getString("generatedBy"),
                });
            }

            billTable.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void viewReturn() {
        try {
            Connection con = Connect();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM return_bill");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("returnBillID");
            model.addColumn("returnDate");
            model.addColumn("totalReturned");
            model.addColumn("generatedBy");

            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString("returnBillID"),
                        resultSet.getString("returnDate"),
                        resultSet.getString("totalReturned"),
                        resultSet.getString("generatedBy"),
                });
            }

            return_Table.setModel(model);

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
        JFrame frame = new JFrame("Bills");

        JLabel label = new JLabel("VIEW Bills", SwingConstants.CENTER);
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 28));
        label.setBounds(0, 10, 750, 30);

        // Create JTable
        billTable = new JTable();
        billTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one row selection
        JScrollPane scrollPane = new JScrollPane(billTable);
        scrollPane.setBounds(50, 100, 700, 300);

        JPanel panel = new JPanel(null);
        panel.add(scrollPane);
        panel.add(label);

        JSeparator separate = new JSeparator();
        separate.setBounds(0, 45, 800, 25);
        panel.add(separate);
        JButton button=new JButton("Returned Bills");
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

        // Add JDateChooser for selecting a specific date
        dateChooser = new JDateChooser();
        dateChooser.setBounds(320, 410, 150, 30);
        JButton filterByDateButton = new JButton("Filter by Date");
        filterByDateButton.setBounds(480, 410, 150, 30);
        filterByDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterByDate();
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                RetGui();

            }
        });

        panel.add(button);
        panel.add(search);
        panel.add(searchButton);
        panel.add(dateChooser);
        panel.add(filterByDateButton);

        frame.add(panel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        viewbills();
    }
    private void RetGui(){
            JFrame f = new JFrame("Return Bills");

            JLabel label = new JLabel("Return Bills", SwingConstants.CENTER);
            label.setFont(new Font(label.getFont().getName(), Font.BOLD, 28));
            label.setBounds(0, 10, 750, 30);

            // Create JTable
            return_Table = new JTable();
            return_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one row selection
            JScrollPane scrollPane = new JScrollPane(return_Table);
            scrollPane.setBounds(50, 100, 700, 300);

            JPanel panel = new JPanel(null);
            panel.add(scrollPane);
            panel.add(label);

            JSeparator separate = new JSeparator();
            separate.setBounds(0, 45, 800, 25);
            panel.add(separate);
            JButton b=new JButton("Back");
            b.setBounds(0, 60, 800, 40);

            JTextField search = new JTextField();
            search.setBounds(50, 410, 150, 30);

            // Create and add a Search button to search for a bill
            JButton searchButton = new JButton("Search");
            searchButton.setBounds(210, 410, 80, 30);
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Call a method to handle searching for a bill
                    searchRet(search.getText());
                }
            });

            // Add JDateChooser for selecting a specific date
            dateChooser = new JDateChooser();
            dateChooser.setBounds(320, 410, 150, 30);
            JButton filterByDateButton = new JButton("Filter by Date");
            filterByDateButton.setBounds(480, 410, 150, 30);
            filterByDateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    filterDate();
                }
            });
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.setVisible(false);
                    ShowGUI();


                }
            });
            panel.add(b);
            panel.add(search);
            panel.add(searchButton);
            panel.add(dateChooser);
            panel.add(filterByDateButton);

            f.add(panel);
            f.setSize(800, 500);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            viewReturn();
        }


    private void searchbill(String searchText) {
        DefaultTableModel model = (DefaultTableModel) billTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        billTable.setRowSorter(sorter);

        // Case-insensitive search
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
    }
    private void searchRet(String searchText) {
        DefaultTableModel model = (DefaultTableModel) return_Table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        return_Table.setRowSorter(sorter);

        // Case-insensitive search
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
    }

    private void filterByDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String selectedDate = sdf.format(dateChooser.getDate());

            DefaultTableModel model = (DefaultTableModel) billTable.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            billTable.setRowSorter(sorter);

            // Filter by the selected date
            sorter.setRowFilter(RowFilter.regexFilter(selectedDate, 1)); // Assuming the date column is at index 1
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error filtering by date: " + e.getMessage());
        }
    }
    private void filterDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String selectedDate = sdf.format(dateChooser.getDate());

            DefaultTableModel model = (DefaultTableModel) return_Table.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            return_Table.setRowSorter(sorter);

            // Filter by the selected date
            sorter.setRowFilter(RowFilter.regexFilter(selectedDate, 1)); // Assuming the date column is at index 1
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error filtering by date: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new bill();
    }
}
