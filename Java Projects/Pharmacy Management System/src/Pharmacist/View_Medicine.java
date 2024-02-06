package Pharmacist;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class View_Medicine extends JFrame{
        private JTable medicineTable;


        public View_Medicine(){
            ShowGUI();
        }

    private void viewMedicines() {
            try {
                Connection con = Connect();
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM medicine");

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("MedicineID");
                model.addColumn("Name");
                model.addColumn("Company Name");
                model.addColumn("MFG Date");
                model.addColumn("Expire Date");
                model.addColumn("MG");
                model.addColumn("Quantity");
                model.addColumn("Price Per Unit");

                while (resultSet.next()) {
                    model.addRow(new Object[]{
                            resultSet.getString("MedicineID"),
                            resultSet.getString("Name"),
                            resultSet.getString("CompanyName"),
                            resultSet.getString("MFGDate"),
                            resultSet.getString("ExpiryDate"),
                            resultSet.getString("MG"),
                            resultSet.getString("Quantity"),
                            resultSet.getString("PricePerUnit")
                    });
                }

                medicineTable.setModel(model);

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
           JFrame frame = new JFrame("Medicine List");

            JLabel label = new JLabel("VIEW Items", SwingConstants.CENTER);
            label.setFont(new Font(label.getFont().getName(), Font.BOLD, 28));
            label.setBounds(0,10,750,30);


            // Create JTable
            medicineTable = new JTable();
            JScrollPane scrollPane = new JScrollPane(medicineTable);
            scrollPane.setBounds(50,70,700,330);

            JPanel panel=new JPanel(null);
            panel.add(scrollPane);
            panel.add(label);

            JSeparator separate=new JSeparator();
            separate.setBounds(0,45,800,25);
            panel.add(separate);


            // Create and add a Refresh button to update the table
            JButton deleteButton = new JButton("Click to Delete");
            deleteButton.setBounds(250, 410, 150, 30);
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteMedicine();
                }
            });



            panel.add(deleteButton);

            frame.add(panel);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            viewMedicines();
        }

    private void deleteMedicine() {
        // Assuming you have a DefaultTableModel named 'model' associated with the JTable
        DefaultTableModel model = (DefaultTableModel) medicineTable.getModel();

        int index = medicineTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Please select a medicine to delete.");
            return;
        }

        String id = model.getValueAt(index, 0).toString();

        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this medicine?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                Connection connection = Connect();

                PreparedStatement st = connection.prepareStatement("DELETE FROM medicine WHERE MedicineID=?");
                st.setString(1, id);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Medicine deleted successfully");

                // Refresh the table
                viewMedicines();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    }
