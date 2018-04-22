package logIn;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import createAccount.CreateAccount;

import data.Task;

import model.GroupListModel;
import model.ObservantTableModel;

import model.SelectedTasksTableModel;

import service.UserService;


 
public class Main {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Time Management Planner");
        final JButton btnLogin = new JButton("Click to login");
        final JButton btnCreateAccount = new JButton("Create Account"); 
        
        btnCreateAccount.addActionListener(
        		new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                    	CreateAccount createAccount = new CreateAccount();
                        createAccount.go(); 
                    }
                    
                });
 
        btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LogInDialog loginDlg = new LogInDialog(frame);
                        loginDlg.setVisible(true);
                        // if login successfully
                        if(loginDlg.isSucceeded()){
                        	//run display page 
                            
                            UserService us = new UserService(checkLogin.getID());
            				GroupListModel listModel = new GroupListModel(us);
            				us.addObserver(listModel);
            				ObservantTableModel<List<Task>> otm = new SelectedTasksTableModel();
            				HomePage app = new HomePage(listModel, otm, us);
            				app.addObserver(otm);
            				app.setVisible(true);
                        }
                    }
                });
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(btnLogin);
        frame.getContentPane().add(btnCreateAccount);
        frame.setVisible(true);
    }
}