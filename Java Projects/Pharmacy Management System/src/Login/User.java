package Login;

import Admin.Dashboard;
import Pharmacist.Pharmacist;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
public class User {
    Connection connection;  // Move the connection variable to the class level

    public String user;
    public User() {

        Log_GUI();
    }

    JFrame frame;
    JTextField tf1, tf2;
    JButton button;
    JLabel pharm,forgotPasswordLabel,Username,Password;
    JPanel panel;

    void Log_GUI() {

        frame = new JFrame("Pharmacy Management System");

        tf1 = new JTextField();
        tf2 = new JTextField();
        button = new JButton("Login");
        pharm = new JLabel("PHARMACY MANAGEMENT SYSTEM");
        pharm.setFont(new Font("Arial", Font.BOLD, 25));
        pharm.setForeground(Color.BLUE);
        Username = new JLabel("Username");
        Password = new JLabel("Password");

        // Create a clickable label for "Forgot Password"
        forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setForeground(Color.blue);
        forgotPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor to hand

        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add the action you want to perform when the label is clicked
                JOptionPane.showMessageDialog(frame, "Please contact to your Admin");
            }
        });
        // Set a fixed size for the frame
        frame.setSize(700, 600);
        frame.setLayout(new BorderLayout());

        // Center the text fields and button
        panel = new JPanel();
        panel.setLayout(null);

        pharm.setBounds(90, 70, 600, 30);
        tf1.setBounds(230, 170, 200, 30);
        tf2.setBounds(230, 210, 200, 30);
        Username.setBounds(130, 170, 200, 30);
        Password.setBounds(130, 210, 200, 30);
        button.setBounds(240, 250, 100, 30);
        forgotPasswordLabel.setBounds(240, 290, 200, 20);

        //add to panel
        panel.add(pharm);
        panel.add(tf1);
        panel.add(tf2);
        panel.add(Username);
        panel.add(Password);
        panel.add(button);
        panel.add(forgotPasswordLabel);

        //add panel to frame
        frame.add(panel, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = tf1.getText();
                String password = tf2.getText();
                login(username,password);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void login(String username, String password) {
        int temp=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "");
            String query = "SELECT * FROM users WHERE username='"+username+"' AND password='"+password+"'";
            Statement st= connection.createStatement();
            ResultSet resultSet= st.executeQuery(query);
            while(resultSet.next()){
                temp=1;
                if (resultSet.getString("user_role").equals("Admin")){
                    frame.setVisible(false);
                    new Dashboard(username);
                }
                else if (resultSet.getString("user_role").equals("Pharmacist")) {
                    frame.setVisible(false);
                    new Pharmacist(username);
                }
            }
            if (temp==0){
                JOptionPane.showMessageDialog(frame,"Wrong Password or Username");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
}