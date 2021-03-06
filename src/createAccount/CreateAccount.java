/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner
 * File Title: CreateAccount
 */
package createAccount;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import data.SQLUserAccountDAO;
import data.UserAccount;
import data.UserAccountDAO;

/*
 * Object that handles user interface and user
 * input of creating an account
 */
public class CreateAccount implements ActionListener {

    JFrame frame;
    JPanel panel;
    JTextField username;
    JTextField email;
    JTextField phoneNum;
    JPasswordField password;
    JPasswordField confirmPassword;
    JLabel warningLabel;


    public void go() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        frame = new JFrame("Create a new account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, panel);

        JLabel userLabel = new JLabel("Username:");
        JLabel emailLabel = new JLabel("Email");
        JLabel phoneNumLabel = new JLabel("Phone Number");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        username = new JTextField(15);
        email = new JTextField(40);
        phoneNum = new JTextField(10);
        password = new JPasswordField(15);
        confirmPassword = new JPasswordField(15);

        GridBagConstraints right = new GridBagConstraints();
        right.anchor = GridBagConstraints.WEST;
        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.EAST;

        right.weightx = (int) 2;
        right.fill = GridBagConstraints.REMAINDER;
        right.gridwidth = GridBagConstraints.REMAINDER;

        //add everything to panel: Decorator Design 
        panel.add(userLabel, left);
        panel.add(username, right);
        panel.add(emailLabel, left);
        panel.add(email, right);
        panel.add(phoneNumLabel, left);
        panel.add(phoneNum, right);
        panel.add(passwordLabel, left);
        panel.add(password, right);
        panel.add(confirmPasswordLabel, left);
        panel.add(confirmPassword, right);

        JButton createAccount = new JButton("Create this account");
        frame.getContentPane().add(BorderLayout.SOUTH, createAccount);

        //actionLister: Command design pattern 
        createAccount.addActionListener(this);

        warningLabel = new JLabel();
        frame.getContentPane().add(BorderLayout.NORTH, warningLabel);
        frame.setSize(700, 500);
        frame.setVisible(true);

    }

    //actionLister implementation: Command
    public void actionPerformed(ActionEvent event) {
        if (!(Arrays.equals(password.getPassword(),
                confirmPassword.getPassword()))) {
            warningLabel
                    .setText("Your passwords do not match! Please try again.");
        } else if (password.getPassword().length < 1) {
            warningLabel
                    .setText("That password is not long enough! Please try again!");
        }else {
            UserAccount user = new UserAccount();
            user.setUserName(username.getText());
            user.setEmail(email.getText());
            user.setPhoneNum(phoneNum.getText());

            StringBuffer buf = new StringBuffer();
            for (char a : password.getPassword()) {
                buf.append(Character.toString(a));
            }
            String passwrd = buf.toString();
            
            user.setPassword(passwrd);

            UserAccountDAO userDAO = new SQLUserAccountDAO();
            try {
                userDAO.save(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(frame,
                    "Account Created!");
            frame.dispose();

        }
    }

}
