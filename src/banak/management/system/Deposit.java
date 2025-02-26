package banak.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
 
 


public class Deposit extends JFrame implements ActionListener {
    
    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3;
    String pin;

    Deposit(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 960, 1080);
        add(l3);
        
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("BACK");
        
        setLayout(null);
        
        l1.setBounds(190, 350, 400, 35);
        l3.add(l1);
        
        t1.setBounds(190, 420, 320, 25);
        l3.add(t1);
        
        b1.setBounds(390, 588, 150, 35);
        l3.add(b1);
        
        b2.setBounds(390, 633, 150, 35);
        l3.add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setSize(960, 1080);
        setUndecorated(true);
        setLocation(500, 0);
        setVisible(true);
    }
    
    private void sendEmail(String recipientEmail, String amount) {
        String host = "smtp.gmail.com"; // For Gmail
        final String user = "cj551634@gmail.com"; // Your email
        final String password = "Chaitu@9322"; // Your email password 

        // Set up the properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Create the session
       // Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(user, password);
//            }
//        });
//
//        try {
//            // Create the email message
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(user));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
//            message.setSubject("Deposit Confirmation");
//            message.setText("Dear Customer,\n\nRs. " + amount + " has been successfully deposited to your account.\n\nThank you for banking with us!");
//
//            // Send the message
//            Transport.send(message);
//            System.out.println("Email sent successfully!");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            System.out.println("Failed to send email.");
//        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {        
            String amount = t1.getText();
            Date date = new Date();
            if (ae.getSource() == b1) {
                if (t1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                } else {
                    Conn c1 = new Conn();
                    c1.s.executeUpdate("insert into bank values('" + pin + "', '" + date + "', 'Deposit', '" + amount + "')");
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");

                    // Call the sendEmail method
                    sendEmail("recipient_email@example.com", amount); // Replace with the actual recipient email

                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            } else if (ae.getSource() == b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Deposit("").setVisible(true);
    }
}
