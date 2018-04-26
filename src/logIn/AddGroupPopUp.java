/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner
 * File Title: AddGroupPopUp
 */

package logIn;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import service.ServiceResponse;
import service.UserService;
import service.UserServiceInterface;

import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import data.Group;


public class AddGroupPopUp extends JFrame {

    /**
     * Generated serialVersion
     */
    private static final long serialVersionUID = -2905946060999378695L;
    private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddGroupPopUp frame = new AddGroupPopUp(new UserService(CheckLogin.getID()));
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AddGroupPopUp(final UserServiceInterface usi) {
        this(usi, null);
    }

    public AddGroupPopUp(final UserServiceInterface usi, Group oldGroup) {

        // Retain reference to this under different name
        final AddGroupPopUp window = this;

        // Make a group or reuse a group
        Group groupInQuestion = oldGroup == null ? new Group() : oldGroup;

        setTitle(oldGroup == null ? "Add new group" : "Change group's name");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        Box verticalBox = Box.createVerticalBox();
        verticalBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        contentPane.add(verticalBox, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        verticalBox.add(panel);

        JLabel lblNewLabel = new JLabel(oldGroup == null ? "New Group's Name" : "Group's New Name");
        panel.add(lblNewLabel);

        JSeparator separator = new JSeparator();
        panel.add(separator);

        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(10);
        textField.setText(groupInQuestion.getName());

        Component verticalGlue = Box.createVerticalGlue();
        verticalBox.add(verticalGlue);

        Box horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setHorizontalAlignment(SwingConstants.LEFT);
        horizontalBox.add(cancelBtn);
        //Command pattern
        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
            }

        });

        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue);

        JButton saveBtn = new JButton("Save");
        saveBtn.setHorizontalAlignment(SwingConstants.RIGHT);
        horizontalBox.add(saveBtn);

        final JLabel warningLabel = new JLabel("");
        warningLabel.setForeground(Color.RED);
        contentPane.add(warningLabel, BorderLayout.NORTH);
        saveBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // update the name with the current text field value
                groupInQuestion.setName(textField.getText());

                // save the new group
                ServiceResponse response = usi.saveGroup(groupInQuestion);

                if (response.isSuccess()) {
                    // dispose of the window
                    window.dispose();
                }

                // something went wrong with saving
                warningLabel.setText(response.getMessage());
            }

        });
    }

}

