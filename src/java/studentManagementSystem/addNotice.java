package studentManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class addNotice extends JFrame implements ActionListener {
    JTextArea notesInput;
    JButton addNoteButton, cancel;
    JLabel notesListLabel;
    JDateChooser dcdob;

    addNotice() {
        // Set up the JFrame
        setSize(600, 400);
        setLocation(650, 300);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Add a header at the top
        JLabel header = new JLabel("THE NOTICE TAKER");
        header.setBounds(150, 10, 400, 30);
        header.setFont(new Font("Serif", Font.BOLD, 24));
        header.setForeground(Color.GREEN.darker());
        add(header);

        // Add the "Give your Notice here" label
        JLabel notesHeader = new JLabel("Give your Notice here");
        notesHeader.setBounds(50, 50, 500, 30);
        notesHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(notesHeader);

        // Add a text area for entering notes
        notesInput = new JTextArea();
        notesInput.setBounds(50, 90, 450, 80);
        notesInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
        notesInput.setLineWrap(true);
        notesInput.setWrapStyleWord(true);
        notesInput.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(notesInput);

        // Add date label
        JLabel lbldob = new JLabel("Date");
        lbldob.setBounds(50, 200, 100, 30);
        lbldob.setFont(new Font("Serif", Font.BOLD, 18));
        add(lbldob);

        // Add date chooser
        dcdob = new JDateChooser();
        dcdob.setBounds(150, 200, 150, 30);
        add(dcdob);

        // Add the "Add Notice" button
        addNoteButton = new JButton("Add Notice");
        addNoteButton.setBounds(150, 260, 100, 30); // Position below date chooser
        addNoteButton.setBackground(Color.BLACK);
        addNoteButton.setForeground(Color.WHITE);
        addNoteButton.addActionListener(this);
        add(addNoteButton);

        // Add the "Cancel" button
        cancel = new JButton("Cancel");
        cancel.setBounds(280, 260, 100, 30); // Positioned next to "Add Notice" button
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        // Set the JFrame visibility
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addNoteButton) {
            String newNote = notesInput.getText().trim();
            java.util.Date selectedDate = dcdob.getDate(); // Get selected date

            // Validate inputs
            if (newNote.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please write something before submitting!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedDate == null) {
                JOptionPane.showMessageDialog(this, "Please select a date before submitting!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Format the date to a SQL-friendly format (YYYY-MM-DD)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(selectedDate);

            // Insert query to save notice and date
            String query = "INSERT INTO addNotice VALUES ('" + newNote + "', '" + formattedDate + "')";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Notice added successfully!");

                // Clear inputs after successful insertion
                notesInput.setText("");
                dcdob.setDate(null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false); // Close the window
        }
    }

    public static void main(String[] args) {
        new addNotice();
    }
}

