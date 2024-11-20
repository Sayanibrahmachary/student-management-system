package studentManagementSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class viewNotice extends JFrame implements ActionListener{
    JTable table;
    JButton print;
    viewNotice()
    {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from addNotice");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 50, 900, 600);
        add(jsp);
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);
        
        setSize(900, 700);
        setLocation(500,200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new viewNotice();
    }
}
