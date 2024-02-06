package Admin;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Update_user {
    private JFrame frame;
    private JComboBox<String> comboBox;
    private JTextField tf1, tf2, tf3, tf4, tf5, tf6;
    private JDateChooser dateChooser;
    public String emailPattern;
    public String MobilePattern;
    public int checkUser;
     Connection connection;
    public Update_user() {
        GUI();

    }
    private void GUI() {

        frame = new JFrame("Update User");
        JLabel label = new JLabel("Update User");
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 28));

        String[] roles = {"Admin", "Pharmacist"};
        comboBox = new JComboBox<>(roles);
        tf1 = new JTextField();
        tf3 = new JTextField();
        tf2 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        JLabel userRole = new JLabel("User Role");
        JSeparator separate = new JSeparator();
        JLabel emailLabel = new JLabel("Id Card No");
        JLabel nameLabel = new JLabel("Name");
        JLabel usernameLabel = new JLabel("Username");
        JLabel dobLabel = new JLabel("Joining date");
        JLabel addressLabel = new JLabel("Address");
        JLabel passwordLabel = new JLabel("Password");
        JLabel mobileLabel = new JLabel("Mobile");


        JButton b1 = new JButton("Search");

        JButton button = new JButton("Update");

        JPanel panel = new JPanel(null);


        label.setBounds(300, 10, 750, 30);
        panel.add(label);

        separate.setBounds(0,45,800,25);
        panel.add(separate);

        usernameLabel.setBounds(100, 50, 100, 30);
        tf2.setBounds(180, 50, 300, 30);
        b1.setBounds(490,50,100,30);
        panel.add(usernameLabel);
        panel.add(tf2);
        panel.add(b1);

        userRole.setBounds(30, 80, 100, 30);
        comboBox.setBounds(30, 120, 300, 30);
        panel.add(userRole);
        panel.add(comboBox);

        emailLabel.setBounds(400, 80, 100, 30);
        tf1.setBounds(400, 120, 300, 30);
        panel.add(emailLabel);
        panel.add(tf1);

        nameLabel.setBounds(30, 150, 100, 30);
        tf3.setBounds(30, 190, 300, 30);
        panel.add(nameLabel);
        panel.add(tf3);



        dobLabel.setBounds(400, 150, 100, 30);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(400, 190, 300, 30);
        panel.add(dobLabel);
        panel.add(dateChooser);

        passwordLabel.setBounds(400, 230, 100, 30);
        tf5.setBounds(400, 270, 300, 30);
        panel.add(passwordLabel);
        panel.add(tf5);

        addressLabel.setBounds(30, 230, 100, 30);
        tf4.setBounds(30, 270, 300, 30);
        panel.add(addressLabel);
        panel.add(tf4);

        mobileLabel.setBounds(30, 310, 100, 30);
        tf6.setBounds(30, 340, 300, 30);
        panel.add(mobileLabel);
        panel.add(tf6);

        button.setBounds(340, 390, 100, 30);
        panel.add(button);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search();
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update();
            }
        });

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

    private void Search() {
        checkUser = 0;
        String username = tf2.getText();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username should not be Empty");
        } else {
            try {
                SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd"); // Update the date format to match the one stored in the database
                Connection con=Connect();
                Statement st = con.createStatement();
                ResultSet resultSet = st.executeQuery("SELECT * FROM users WHERE username='" + username + "'");

                while (resultSet.next()) {
                    checkUser = 1;
                    tf2.setEditable(false);
                    tf1.setText(resultSet.getString("IdCardNo"));
                    tf3.setText(resultSet.getString("name"));
                    tf4.setText(resultSet.getString("Salary"));
                    tf5.setText(resultSet.getString("password"));
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
                    if (resultSet.getString("user_role").equals("Admin")) {
                        comboBox.removeAllItems();
                        comboBox.addItem("Admin");
                        comboBox.addItem("Pharmacist");
                    } else {
                        comboBox.removeAllItems();
                        comboBox.addItem("Pharmacist");
                        comboBox.addItem("Admin");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            if (checkUser == 0) {
                JOptionPane.showMessageDialog(null, "User not Exist");
            }
        }
    }


    private void Update() {
        MobilePattern = "^[0-9]*$";

        String user_role = (String) comboBox.getSelectedItem();
        String name = tf3.getText();
        SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateChooser.getDate();
        String Joiningdate = (date != null) ? DF.format(date) : "";
        String MobileNum = tf6.getText();
        String IdCardNo = tf1.getText();
        String username = tf2.getText();
        String password = tf5.getText();
        String  Salary = tf4.getText();

        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null,"Name should not be Empty");
        }
        else if(Joiningdate.isEmpty()){
            JOptionPane.showMessageDialog(null,"Joining Date should not be Empty");
        }
        else if(Salary.isEmpty()){
            JOptionPane.showMessageDialog(null,"address should not be Empty");
        }
        else if (IdCardNo.isEmpty()){
            JOptionPane.showMessageDialog(null,"ID Card No. should not be Empty");
        }

        else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Password should not be Empty");
        }
        else if(MobileNum.isEmpty()){
            JOptionPane.showMessageDialog(null,"Mobile Number should not be Empty");
        }
        else if (!IdCardNo.matches(MobilePattern) || IdCardNo.length()!= 14){
            JOptionPane.showMessageDialog(null,"ID card Number is invalid");
        }
        else if (!MobileNum.matches(MobilePattern) || MobileNum.length()!= 11){
            JOptionPane.showMessageDialog(null,"Mobile Number is invalid");
        } else {
            try {
               Connection con=Connect();
                SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Convert the Java date to MySQL date format
                String mysqlDate = (date != null) ? mysqlDateFormat.format(date) : "";

                PreparedStatement preparedStatement = con.prepareStatement("UPDATE users SET user_role=?, name=?, Joiningdate=?, mobile_number=?, IdCardNo=?, password=?, Salary=? WHERE username=?");
                preparedStatement.setString(1, user_role);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, mysqlDate);
                preparedStatement.setString(4, MobileNum);
                preparedStatement.setString(5, IdCardNo);
                preparedStatement.setString(6, password);
                preparedStatement.setString(7, Salary);
                preparedStatement.setString(8, username);

                preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(null, "User Updated Successfully");
                    frame.setVisible(false);
                    new Update_user();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
}
