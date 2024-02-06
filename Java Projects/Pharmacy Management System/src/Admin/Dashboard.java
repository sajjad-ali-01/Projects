package Admin;
import Login.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Dashboard extends JFrame{
    private JFrame frame;
    private JLabel Lable;
    private JButton Add_User,bills,view,userupdate_user,logout,exit;
    private JPanel panel;
    JSeparator separate;
    private String username;
    public Dashboard(){
    }
    public Dashboard(String user){
        this.username=user;
        GUI();
    }
    private void GUI(){

        frame =new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Lable = new JLabel("ADMIN DASHBOARD",SwingConstants.CENTER );
        Lable.setBounds(0,25,1300,50);
        Lable.setFont(new Font("Arial", Font.BOLD, 30));
        Lable.setForeground(Color.WHITE);

        separate = new JSeparator();
        separate.setBounds(0, 70, 1370, 10);


        String imagePath = "src/Admin/images/add_user.png";
        ImageIcon icon = new ImageIcon(imagePath);
        Add_User = new JButton("ADD USER", icon);
        Add_User.setBackground(Color.BLACK);
        Add_User.setFont(new Font("Arial", Font.BOLD, 20));
        Add_User.setForeground(Color.WHITE);
        Add_User.setBounds(300, 150, 300, 100);

        String pimagePath = "src/Admin/images/bills.jpg";
        ImageIcon picon = new ImageIcon(pimagePath);
        bills=new JButton("Bills",picon);
        bills.setBackground(Color.BLACK);
        bills.setFont(new Font("Arial", Font.BOLD, 20));
        bills.setForeground(Color.WHITE);
        bills.setBounds(700,150,300,100);

        String vimagePath = "src/Admin/images/view.jpg";
        ImageIcon vicon = new ImageIcon(vimagePath);
        view=new JButton("View",vicon);
        view.setBackground(Color.BLACK);
        view.setFont(new Font("Arial", Font.BOLD, 20));
        view.setForeground(Color.WHITE);
        view.setBounds(300,300,300,100);

        String uimagePath = "src/Admin/images/update.png";
        ImageIcon uicon = new ImageIcon(uimagePath);
        userupdate_user=new JButton("Update",uicon);
        userupdate_user.setBackground(Color.BLACK);
        userupdate_user.setFont(new Font("Arial", Font.BOLD, 20));
        userupdate_user.setForeground(Color.WHITE);
        userupdate_user.setBounds(700,300,300,100);

        String limagePath = "src/Admin/images/logout.png";
        ImageIcon licon = new ImageIcon(limagePath);
        logout=new JButton("Logout",licon);
        logout.setBackground(Color.BLACK);
        logout.setFont(new Font("Arial", Font.BOLD, 20));
        logout.setForeground(Color.WHITE);
        logout.setBounds(300,450,300,100);

        String eimagePath = "src/Admin/images/exit.png";
        ImageIcon eicon = new ImageIcon(eimagePath);
        exit=new JButton("Exit",eicon);
        exit.setBackground(Color.BLACK);
        exit.setFont(new Font("Arial", Font.BOLD, 20));
        exit.setForeground(Color.WHITE);
        exit.setBounds(700,450,300,100);

        panel =new JPanel(null);

        panel.add(Add_User);
        panel.add(separate);
        panel.add(bills);
        panel.add(view);
        panel.add(userupdate_user);
        panel.add(logout);
        panel.add(exit);

        panel.setBackground(Color.DARK_GRAY);

        frame.add(Lable,BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);

        Add_User.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Add_user();
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new view_user(username);
            }
        });
        userupdate_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Update_user();
            }
        });
        bills.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new bills();
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null,"Do you want to Logout ","Select",JOptionPane.YES_NO_OPTION);
                if(a==0){frame.setVisible(false);
                    new User();
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null,"Do you want to Exit ","Select",JOptionPane.YES_NO_OPTION);
                if(a==0){
                    System.exit(0);
                }
            }
        });
        frame.setSize(1368,766);
        frame.setVisible(true);
    }
}