package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class view_user extends JFrame {

    JFrame frame;
    JButton button;
    JTable table;
    JLabel label;
    JPanel panel;
    JSeparator separate;

    DefaultTableModel model;
    private String username = " ";

    public view_user() {
    }
    public view_user(String tempUsername) {
        GUI();
        username = tempUsername;
        Data();
    }

    private void GUI() {
        frame = new JFrame("view user");
        label = new JLabel("View USER", SwingConstants.CENTER);
        separate = new JSeparator();
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 28));
        button = new JButton("Click to delete User");

        // Create a DefaultTableModel with column names
        String[] columnNames = {"ID", "Name", "Role", "Joining date", "Mobile", " Id Card No", "Username", "Password", "Salary"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);


        // Add JScrollPane to the table
        JScrollPane scrollPane = new JScrollPane(table);

        label.setBounds(0, 10, 750, 30);
        button.setBounds(250, 420, 250, 30);
        scrollPane.setBounds(15, 50, 750, 350);
        separate.setBounds(0, 40, 800, 10);

        panel = new JPanel(null);
        panel.add(button);
        panel.add(label);
        panel.add(scrollPane); // Add the JScrollPane instead of the table
        panel.add(separate);

        button.addActionListener(e -> deleteSelectedUser());

        // Add the mouse-clicked event to trigger actions when a row is clicked
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Single click
                    // You can add more logic here if needed
                }
            }
        });

        frame.add(panel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void Data() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users ");
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("user_role"),
                        resultSet.getString("Joiningdate"),
                        resultSet.getString("mobile_number"),
                        resultSet.getString("IdCardNo"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("Salary")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void deleteSelectedUser() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to delete.");
            return;
        }

        String id = model.getValueAt(index, 0).toString();
        String usernameTable = model.getValueAt(index, 6).toString();
        if (username.equals(usernameTable)) {
            JOptionPane.showMessageDialog(null, "You cannot delete your own account.");
        } else {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "");

                    PreparedStatement st = connection.prepareStatement("DELETE FROM users WHERE id=?");
                    st.setString(1, id);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "User deleted successfully");

                    // Refresh the table
                    frame.setVisible(false);
                    new view_user(username).setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }
}
