package Pharmacist;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.Random;
import java.util.Date;

public class Return_medicine {

    private String username;
    private DefaultTableModel tableModel;
    private JTable Tables;
    private  JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7;
    private String[] id;
    private int indexrow=0;
    private final String numPatern="^[0-9]*$";
    private Map<String, Integer> medicineQuantityMap = new HashMap<>();
    private int totalQuantity=0;
    private double finalTotalprice = 0.0;
    public Return_medicine(String user){
        this.username=user;
        Sell();
    }
    public void Sell() {
        JFrame frame = new JFrame("Pharmacy management  System");
        JLabel lable = new JLabel("Return Items", SwingConstants.CENTER);
        lable.setBounds(0, 10, 1300, 50);
        lable.setFont(new Font("Arial", Font.BOLD, 30));

        JSeparator separate = new JSeparator();
        separate.setBounds(0, 55, 1370, 10);

        JLabel Search = new JLabel("Search");
        Search.setBounds(930, 65, 50, 30);
        Search.setFont(new Font("Arial", Font.BOLD, 15));

        tf1 = new JTextField();
        tf1.setBounds(800, 95, 400, 40);
        tf1.setFont(new Font("Arial", Font.BOLD, 15));

        String[] columnNames = {"Medicines"};
        tableModel = new DefaultTableModel(null, columnNames);
        Tables = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(Tables);
        scrollPane.setBounds(800, 165, 520, 1400);

        JLabel Medicine = new JLabel("Name");
        Medicine.setBounds(20, 85, 100, 30);
        Medicine.setFont(new Font("Arial", Font.BOLD, 15));
        tf2 = new JTextField();
        tf2.setBounds(20, 125, 300, 35);
        tf2.setFont(new Font("Arial", Font.BOLD, 13));

        JLabel Price = new JLabel("Price Per Unit");
        Price.setBounds(400, 85, 100, 30);
        Price.setFont(new Font("Arial", Font.BOLD, 15));
        tf3 = new JTextField();
        tf3.setBounds(400, 125, 300, 35);
        tf3.setFont(new Font("Arial", Font.BOLD, 13));


        JLabel Name = new JLabel("Quantity Available");
        Name.setBounds(20, 155, 150, 30);
        Name.setFont(new Font("Arial", Font.BOLD, 15));
        tf4 = new JTextField();
        tf4.setBounds(20, 195, 300, 35);
        tf4.setFont(new Font("Arial", Font.BOLD, 13));


        JLabel units = new JLabel("No. of Units");
        units.setBounds(400, 155, 100, 30);
        units.setFont(new Font("Arial", Font.BOLD, 15));
        tf5 = new JTextField();
        tf5.setBounds(400, 195, 300, 35);
        tf5.setFont(new Font("Arial", Font.BOLD, 13));

        JLabel company = new JLabel("Company Name");
        company.setBounds(20, 225, 150, 30);
        company.setFont(new Font("Arial", Font.BOLD, 15));
        tf6 = new JTextField();
        tf6.setBounds(20, 265, 300, 35);
        tf6.setFont(new Font("Arial", Font.BOLD, 13));


        JLabel total = new JLabel("Total Price");
        total.setBounds(400, 225, 100, 30);
        total.setFont(new Font("Arial", Font.BOLD, 15));
        tf7 = new JTextField();
        tf7.setBounds(400, 265, 300, 35);
        tf7.setFont(new Font("Arial", Font.BOLD, 13));

        JButton button = new JButton("Add to Return");
        button.setBounds(200, 315, 150, 30);

        JLabel RS = new JLabel("RS: ");
        RS.setBounds(330, 630, 100, 30);
        RS.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel p = new JLabel("00");
        p.setBounds(370, 630, 100, 30);
        p.setFont(new Font("Arial", Font.BOLD, 20));

        String[] columns = {"SerialNo", "ID", "Name", "Rack", "Price Per Unit", "No. of Unit", "Total Price"};
        DefaultTableModel Model = new DefaultTableModel(null, columns);
        JTable table = new JTable(Model);
        JScrollPane scrollPanes = new JScrollPane(table);
        scrollPanes.setBounds(20, 360, 700, 260);

        JButton removeButton = new JButton("Remove Item");
        removeButton.setBounds(350, 315, 150, 30);
        JButton Bill = new JButton("Print Bill");

        JButton Return=new JButton("Return");
        Return.setBounds(470, 630, 150, 30);

        JPanel panel = new JPanel(null);

        panel.add(Return);
        panel.add(Bill);
        panel.add(removeButton);
        panel.add(lable);
        panel.add(separate);
        panel.add(Search);
        panel.add(tf1);
        panel.add(scrollPane);
        panel.add(tf2);
        panel.add(Medicine);
        panel.add(Price);
        panel.add(tf3);
        panel.add(Name);
        panel.add(tf4);
        panel.add(units);
        panel.add(tf5);
        panel.add(company);
        panel.add(tf6);
        panel.add(total);
        panel.add(tf7);
        panel.add(button);
        panel.add(RS);
        panel.add(p);
        panel.add(scrollPanes);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                MedicineName("");
                tf2.setEditable(false);
                tf3.setEditable(false);
                tf4.setEditable(false);
                tf6.setEditable(false);
                tf7.setEditable(false);
            }
        });
        tf1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                clearFields();
                String search = tf1.getText();
                MedicineName(search);
            }
        });
        ListSelectionModel selectionModel = Tables.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = Tables.getSelectedRow();
                if (selectedRow != -1) {
                    TableModel model = Tables.getModel();
                    String nameOrID = model.getValueAt(selectedRow, 0).toString();
                    id = nameOrID.split("~");
                    String mg = model.getValueAt(selectedRow, 0).toString();

                    try {
                        Connection connection = Connect();
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM medicine WHERE MedicineID = '" + id[0].trim() + "'");

                        if (rs.next()) {
                            tf2.setText(rs.getString("Name"));
                            tf3.setText(rs.getString("PricePerUnit"));
                            tf6.setText(rs.getString("CompanyName"));

                            // Update available quantity using the sum of quantities
                            totalQuantity = medicineQuantityMap.getOrDefault(id[0].trim(), 0);
                            tf4.setText(String.valueOf(totalQuantity));
                        }
                    } catch (Exception ex) {
                        JOptionPane.showConfirmDialog(null, ex);
                    }
                }
            }
        });
        tf5.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String noUnits = tf5.getText();
                String price = tf3.getText();
                if (!noUnits.isEmpty()) {

                    if (!noUnits.matches(numPatern)) {
                        JOptionPane.showMessageDialog(null, "Invalid Number of Units ");
                        tf5.setText("");
                        tf7.setText("");
                    } else {
                        double parsePrice = Double.parseDouble(price);
                        int parseUnits = Integer.parseInt(noUnits);
                        double totalPrice = parsePrice * parseUnits;
                        tf7.setText(String.valueOf(totalPrice));
                    }
                } else {
                    tf7.setText("");
                }
            }

        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Remove the selected row from the cart table
                    Model.removeRow(selectedRow);
                    // Update serial numbers in the cart table
                    updateSerialNumbers(Model);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a row to remove.");
                }
            }
        });

// Modify the ActionListener for the "Add to cart" button to add the item to the cart
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int serialNumber = Model.getRowCount() + 1;
                String totalprice = tf7.getText();
                String name = tf2.getText();
                String units = tf5.getText();
                int unit = Integer.parseInt(tf5.getText());


                if (units.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Number of units is required");
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a Medicine");
                } else {
                    boolean alreadyInCart = false;
                    int existingRow = -1;

                    // Check if the medicine is already in the cart
                    for (int i = 0; i < Model.getRowCount(); i++) {
                        String medicineIDInCart = (String) Model.getValueAt(i, 1); // Assuming MedicineID is in the second column
                        if (medicineIDInCart.equals(id[0].trim())) {
                            alreadyInCart = true;
                            existingRow = i;
                            break;
                        }
                    }

                    try {
                        Connection connection = Connect();
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM medicine WHERE MedicineID = '" + id[0].trim() + "'");

                        if (rs.next()) {
                            int Quant = Integer.parseInt(tf4.getText());

                                if (alreadyInCart) {
                                    // If the medicine is already in the cart, update the row
                                    String existingQuantity = Model.getValueAt(existingRow, 5).toString();
                                    String existingTotalPrice = Model.getValueAt(existingRow, 6).toString();

                                    // Calculate the difference in quantity
                                    int quantityDifference = unit - Integer.parseInt(existingQuantity);

                                    // Update the row with the new values
                                    updateRow(Model, units, totalprice, existingRow);


                                    // Update finalTotalprice based on the quantity difference
                                    finalTotalprice += Double.parseDouble(tf7.getText()) - Double.parseDouble(existingTotalPrice);
                                } else {
                                    // If the medicine is not in the cart, add a new row
                                    Model.addRow(new Object[]{serialNumber, id[0].trim(), rs.getString("name"), rs.getString("MG"), rs.getString("PricePerUnit"), units, totalprice});
                                    finalTotalprice += Double.parseDouble(tf7.getText());
                                }

                                // Update the final total price label
                                p.setText(String.valueOf((int) finalTotalprice));
                            }
                        clearFields();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });


        ListSelectionModel selectModel = table.getSelectionModel();
        selectModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    TableModel model = table.getModel();
                    String ID = model.getValueAt(selectedRow, 1).toString();
                    indexrow = selectedRow;
                    String Qunt = model.getValueAt(selectedRow, 5).toString();
                    String mg = model.getValueAt(selectedRow, 0).toString();

                    try {
                        Connection connection = Connect();
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM medicine WHERE MedicineID = '" + ID + "'");

                        if (rs.next()) {
                            tf4.setText(String.valueOf(totalQuantity));
                            tf2.setText(rs.getString("Name"));
                            tf3.setText(rs.getString("PricePerUnit"));
                            tf5.setText(Qunt);
                            tf6.setText(rs.getString("CompanyName"));
                        }
                    } catch (Exception ex) {
                        JOptionPane.showConfirmDialog(null, ex);
                    }
                }
            }
        });
        Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String returnBillId = generateReturnBillId();
                String Billid = JOptionPane.showInputDialog("Enter Bill id");

                try {
                    Connection con = Connect();
                    Statement st = con.createStatement();

                    // Check if the bill with the entered ID exists
                    ResultSet billResultSet = st.executeQuery("SELECT * FROM bill WHERE billId = '" + Billid + "'");

                    if (billResultSet.next()) {
                        // The bill exists, proceed with the return
                        if (finalTotalprice != 0) {
                            try {
                                // Get the current date and time for generating a unique filename
                                SimpleDateFormat filenameDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                                SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                                String filenameTimestamp = filenameDateFormat.format(new Date());
                                String fileTimestamp = DateFormat.format(new Date());

                                // Specify the file path where the PDF will be generated
                                String filePath = "E:\\" + "ReturnBill_" + filenameTimestamp + ".pdf";

                                Document document = new Document();
                                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                                document.open();
                                document.add(new Paragraph("                                                SF PHARMACY - RETURN BILL\n\n"));
                                document.add(new Paragraph("Pos:   221                                             Mop:  Cash Sales"));
                                document.add(new Paragraph("Return Bill ID:      " + returnBillId));
                                document.add(new Paragraph("Date:     " + fileTimestamp));

                                int rowCount = table.getRowCount();

                                for (int i = 0; i < rowCount; i++) {
                                    try {
                                        // Get details from the cart table
                                        String medicineID = (String) Model.getValueAt(i, 1);
                                        int quantityToReturn = Integer.parseInt((String) Model.getValueAt(i, 5));
                                        String expiryDate = getExpiryDateFromDatabase(medicineID);

                                        // Update quantity in the first available row
                                        String updateQuery = "UPDATE medicine SET Quantity = Quantity + " + quantityToReturn +
                                                " WHERE MedicineID = '" + medicineID + "' AND ExpiryDate = '" + expiryDate + "'";
                                        st.executeUpdate(updateQuery);

                                        String serial = "";
                                        String name = "";
                                        String pricePerUnit = "";
                                        String units = "";
                                        String totalprice = "";

                                        if (Model.getValueAt(i, 0) != null) {
                                            serial = Model.getValueAt(i, 0).toString();
                                        }

                                        if (Model.getValueAt(i, 2) != null) {
                                            name = Model.getValueAt(i, 2).toString();
                                        }

                                        if (Model.getValueAt(i, 4) != null) {
                                            pricePerUnit = Model.getValueAt(i, 4).toString();
                                        }

                                        if (Model.getValueAt(i, 5) != null) {
                                            units = Model.getValueAt(i, 5).toString();
                                        }

                                        if (Model.getValueAt(i, 6) != null) {
                                            totalprice = Model.getValueAt(i, 6).toString();
                                        }

                                        if (i == 0) {
                                            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                                            document.add(new Paragraph(String.format("%-15s %-25s %-15s %-15s %-15s",
                                                    "SNo", "Product", "Price", "Qnt", "Total Price")));
                                        }

                                        document.add(new Paragraph(String.format("%-15s %-25s %-15s %-15s %-15s",
                                                serial, name, pricePerUnit, units, totalprice)));
                                    } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(null, ex);
                                    }
                                }

                                document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                                document.add(new Paragraph("                                                                               Total Returned:              " + finalTotalprice));
                                document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                                document.add(new Paragraph("Thank you for the return"));
                                document.add(new Paragraph("please visit again..."));

                                SimpleDateFormat Dformat = new SimpleDateFormat("yyyy-MM-dd");
                                Calendar cal = Calendar.getInstance();
                                PreparedStatement ps = con.prepareStatement("INSERT INTO return_bill (returnBillID, returnDate, totalReturned, generatedBy) VALUES (?, ?, ?, ?)");
                                ps.setString(1, returnBillId);
                                ps.setString(2, Dformat.format(cal.getTime()));
                                ps.setDouble(3, finalTotalprice);
                                ps.setString(4, username);
                                ps.executeUpdate();

                                // Reduce the final total amount from the bill total paid
                                String updateBillQuery = "UPDATE bill SET totalPaid = totalPaid - " + finalTotalprice +
                                        " WHERE billId = '" + Billid + "'";
                                st.executeUpdate(updateBillQuery);

                                // Optionally, clear the cart table after generating the return bill
                                Model.setRowCount(0);
                                // Optionally, update the final total price label
                                p.setText("00");
                                clearFields();

                                // Close the document to finalize the PDF
                                document.close();

                                // Open the generated PDF automatically
                                Desktop.getDesktop().open(new File(filePath));
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select some medicines to return");
                        }
                    } else {
                        // The bill with the entered ID does not exist
                        JOptionPane.showMessageDialog(null, "Bill with ID " + Billid + " does not exist");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });




        frame.add(panel);
        frame.setSize(1368, 766);
        frame.setVisible(true);
    }

    public static String generateBillId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = dateFormat.format(new Date());

        Random random = new Random();
        int randomNum = random.nextInt(1000); // You can adjust the range as needed

        return timestamp + String.format("%04d", randomNum);
    }
    public static String generateReturnBillId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = dateFormat.format(new Date());

        Random random = new Random();
        int randomNum = random.nextInt(1000); // You can adjust the range as needed

        return timestamp + String.format("%04d", randomNum);
    }
    private String getNextExpiryDate(String medicineID, String currentExpiryDate) {
        try {
            Connection connection = Connect();
            Statement st = connection.createStatement();

            // Query to get the next expiry date for a specific medicineID after the current expiry date
            String query = "SELECT ExpiryDate FROM medicine WHERE MedicineID = '" + medicineID +
                    "' AND ExpiryDate > '" + currentExpiryDate + "' ORDER BY ExpiryDate ASC LIMIT 1";

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                return rs.getString("ExpiryDate");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return null; // Return null if no next expiry date is found
    }

    // Add a method to get the available quantity for a given MedicineID and ExpiryDate from the database
    private int getAvailableQuantity(String medicineID, String expiryDate) {
        try {
            Connection connection = Connect();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT Quantity FROM medicine WHERE MedicineID = '" + medicineID + "' AND ExpiryDate = '" + expiryDate + "'");
            if (rs.next()) {
                return rs.getInt("Quantity");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return 0;
    }

    // Add a method to get the expiry date for a given MedicineID from the database
    private String getExpiryDateFromDatabase(String medicineID) {
        try {
            Connection connection = Connect();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT ExpiryDate FROM medicine WHERE MedicineID = '" + medicineID + "' ORDER BY ExpiryDate ASC LIMIT 1");
            if (rs.next()) {
                return rs.getString("ExpiryDate");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    private double getTotalPrice(String medicineID, String expiryDate) {
        try {
            Connection connection = Connect();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT PricePerUnit * Quantity AS TotalPrice FROM medicine WHERE MedicineID = '" + medicineID + "' AND ExpiryDate = '" + expiryDate + "'");

            if (rs.next()) {
                return rs.getDouble("TotalPrice");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0.0;
    }

    private void updateSerialNumbers(DefaultTableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0); // Update the serial number in the first column
        }
    }
    private void updateRow(DefaultTableModel model, String Quant, String price, int rowIndex) {
        model.setValueAt(Quant, rowIndex, 5);
        model.setValueAt(price, rowIndex, 6);
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

    private void MedicineName(String NameorID) {
        int TotalPrice = 0;

        tableModel = (DefaultTableModel) Tables.getModel();
        tableModel.setRowCount(0);

        try {
            Connection connection = Connect();
            Statement st = connection.createStatement();

            // Use GROUP BY to group rows by MedicineID and show the sum of quantity
            String query = "SELECT MedicineID, Name, MG, SUM(Quantity) AS TotalQuantity FROM medicine WHERE Name LIKE '" + NameorID + "%' OR MedicineID LIKE '" + NameorID + "%' GROUP BY MedicineID, Name, MG";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String medicineID = rs.getString("MedicineID");
                String name = rs.getString("Name");
                int totalQuantity = rs.getInt("TotalQuantity");

                // Update the map with the total quantity for the medicine ID
                medicineQuantityMap.put(medicineID, totalQuantity);

                tableModel.addRow(new Object[]{medicineID + "~ " + name});
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    private void clearFields(){
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
        tf5.setText("");
        tf6.setText("");
        tf7.setText("");
    }
}