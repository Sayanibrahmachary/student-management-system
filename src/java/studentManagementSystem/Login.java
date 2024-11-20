package studentManagementSystem;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    
    JButton login, cancel;
    JTextField tfusername, tfpassword;
    
    Login()
    {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        //for username;
        JLabel lb1username=new JLabel("Username");
        lb1username.setBounds(40,20,100,20);
        add(lb1username);
        
        tfusername=new JTextField();
        tfusername.setBounds(150,20,150,20);
        add(tfusername);
        
        //for password
        JLabel lb1password=new JLabel("Password");
        lb1password.setBounds(40,70,100,20);
        add(lb1password);
        
        tfpassword=new JPasswordField();
        tfpassword.setBounds(150,70,150,20);
        add(tfpassword);
        
        //button
        login=new JButton("Login");
        login.setBounds(40,140,120,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setFont(new Font("Tahoma",Font.BOLD,16));
        add(login);
        
        //cancle
        cancel=new JButton("Cancel");
        cancel.setBounds(180,140,120,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma",Font.BOLD,16));
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 20, 200, 200);
        add(image);
        
        
        setSize(600,300);
        setLocation(610,390);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)  
    {
        if (ae.getSource() == login) {
            String username = tfusername.getText();
            String password = tfpassword.getText();
            
            String query = "select * from login where username='"+username+"' and password='"+password+"'";
            
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                
                if (rs.next()) {
                    setVisible(false);
                    new Project();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    setVisible(false);
                }
                } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }
    public static void main(String[] args)
    {
        new Login();
    }
}
