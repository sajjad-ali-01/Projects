package Pharmacist;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class profile {
    private JFrame frame;
    private JTextField tf1, tf3, tf4, tf6;
    private JDateChooser dateChooser;
    String username;
    Connection connection;
    public profile(){

    }
    public profile(String user) {
        this.username=user;
        GUI();
        profil();
    }
    private void GUI() {

        frame = new JFrame("Profile");
        JLabel label = new JLabel("Profile");
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 28));

        String imagePath = "src/Admin/images/profiles.jpg";
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel l1 = new JLabel(icon);

        tf1 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf6 = new JTextField();
        JSeparator separate = new JSeparator();
        JLabel emailLabel = new JLabel("ID Card No");
        JLabel nameLabel = new JLabel("Name");
        JLabel dobLabel = new JLabel("Joining Date");
        JLabel addressLabel = new JLabel("Salary");
        JLabel mobileLabel = new JLabel("Mobile");
        JLabel user=new JLabel(username);
        user.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        label.setBounds(350, 10, 750, 30);
        panel.add(label);

        separate.setBounds(0,45,800,25);
        panel.add(separate);

        l1.setBounds(80,110,150,150);
        user.setBounds(118,260,80,30);
        panel.add(user);
        panel.add(l1);

        nameLabel.setBounds(400, 50, 100, 30);
        tf3.setBounds(400, 80, 300, 30);
        panel.add(nameLabel);
        panel.add(tf3);

        emailLabel.setBounds(400, 110, 100, 30);
        tf1.setBounds(400, 150, 300, 30);
        panel.add(emailLabel);
        panel.add(tf1);

        dobLabel.setBounds(400, 180, 100, 30);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(400, 220, 300, 30);
        panel.add(dobLabel);
        panel.add(dateChooser);

        mobileLabel.setBounds(400, 250, 100, 30);
        tf6.setBounds(400, 290, 300, 30);
        panel.add(mobileLabel);
        panel.add(tf6);

        addressLabel.setBounds(400, 320, 100, 30);
        tf4.setBounds(400, 360, 300, 30);
        panel.add(addressLabel);
        panel.add(tf4);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private Connection Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "");
            return connection;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    private void profil() {
        try {
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd"); // Update the date format to match the one stored in the database
            Connection con=Connect();
            Statement st = con.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM users WHERE username='" + username + "'");

            while (resultSet.next()) {
                tf1.setText(resultSet.getString("IdCardNo"));
                tf3.setText(resultSet.getString("name"));
                tf4.setText(resultSet.getString("Salary"));
                tf6.setText(resultSet.getString("mobile_number"));

                String dob = resultSet.getString("Joiningdate");
                if (!dob.isEmpty()) {
                    try {
                        Date date = dFormat.parse(dob);
                        dateChooser.setDate(date);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
