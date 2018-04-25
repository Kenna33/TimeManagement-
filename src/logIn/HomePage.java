package logIn; 

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import service.GroupService;
import service.GroupServiceInterface;
import service.ServiceResponse;
import service.UserServiceInterface;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListModel;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;


import data.Group;
import data.Task;
import model.ObservantTableModel;

import javax.swing.JButton;

public class HomePage extends Observable {

	private JFrame TimeManagementHome;
	private JTable TasksTable;
	private Group selectedGroup;
	private Task clickedTask;
	//private Task clickedTask;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public HomePage(ListModel<Group> glm, ObservantTableModel<List<Task>> st, UserServiceInterface usi) {
		initialize(glm, st, usi);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ListModel<Group> glm, ObservantTableModel<List<Task>> otm, final UserServiceInterface usi) {

		// Make a GroupListModel for the list
		ListModel<Group> groupListModel = glm;

		// Make a SelectedTaskTableModel
		ObservantTableModel<List<Task>> selectedTasks = otm;
		
		TimeManagementHome = new JFrame();
		TimeManagementHome.setTitle("Time Management Planner");
		TimeManagementHome.setBounds(100, 100, 450, 300);
		TimeManagementHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JSplitPane splitPane = new JSplitPane();
		TimeManagementHome.getContentPane().add(splitPane, BorderLayout.CENTER);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setBackground(UIManager.getColor("Button.background"));
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(splitPane_1);

		JLabel lblNewLabel = new JLabel("Groups");
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane_1.setLeftComponent(lblNewLabel);

		Box verticalBox = Box.createVerticalBox();
		splitPane_1.setRightComponent(verticalBox);

		JList<Group> groupList = new JList<>(groupListModel);
		groupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		groupList.setBackground(new Color(255, 255, 255));
		verticalBox.add(groupList.getName(), groupList);
		
		/*
		JTable taskList = new JTable(selectedTasks);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskList.setBackground(new Color(255, 255, 255));
		verticalBox.add(taskList.getName(), taskList);
		*/

		JSeparator separator = new JSeparator();
		verticalBox.add(separator);

		Component horizontalStrut = Box.createHorizontalStrut(150);
		verticalBox.add(horizontalStrut);

		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_2);

		JLabel lblNewLabel_1 = new JLabel("Tasks");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane_2.setLeftComponent(lblNewLabel_1);

		Box verticalBox_1 = Box.createVerticalBox();
		splitPane_2.setRightComponent(verticalBox_1);
		
		//final HomePage mySecondHomePage = this; 
		
		
		
		TasksTable = new JTable(selectedTasks);
		TasksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TasksTable.setFillsViewportHeight(true);

		//final HomePage myHomePage2 = this; 
		TasksTable.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent me) {
				
				JTable table = (JTable) me.getSource();
			
				ObservantTableModel<Task> taskModel = (ObservantTableModel<Task>)table.getModel();
				int selected = table.getSelectedRow();
				if(selected >= 0) {
					clickedTask = taskModel.getObservedValue(selected);
				}
				
				/*
				for(Integer taskIndex: selected) {
					clickedTasks.add(allSelectedTask.get(taskIndex));
				}
				*/
			 //   myHomePage2.setChanged();
			//	myHomePage2.notifyObservers(selectedTasks);
				
			}
		});;
		
	
		
		JScrollPane scrollPane = new JScrollPane(TasksTable);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		verticalBox_1.add(scrollPane);
		

		Component horizontalStrut_1 = Box.createHorizontalStrut(200);
		verticalBox_1.add(horizontalStrut_1);

		JMenuBar menuBar = new JMenuBar();
		TimeManagementHome.setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem addGroupBtn = new JMenuItem("Add Group");
		addGroupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						AddGroupPopUp popUp = new AddGroupPopUp(usi);
						popUp.setVisible(true);
					}
				});
			}
		});
		mnMenu.add(addGroupBtn);
		
		
		final JLabel warningLabel = new JLabel("");
		warningLabel.setForeground(Color.RED);
		menuBar.add(warningLabel);
		
		
		JMenuItem editGroupBtn = new JMenuItem("Edit Group");
		editGroupBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selectedGroup == null) {
					warningLabel.setForeground(Color.RED);
					warningLabel.setText("Select a Group first!!!");
					return;
				}
				AddGroupPopUp popUp = new AddGroupPopUp(usi, selectedGroup);
				groupList.clearSelection();
				selectedGroup = null; 
				popUp.setVisible(true);
			}
			
		});
		mnMenu.add(editGroupBtn);

		JMenuItem removeGroupBtn = new JMenuItem("Remove Group");
		removeGroupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedGroup == null) {
					warningLabel.setForeground(Color.RED);
					warningLabel.setText("Select a Group first!!");
					return;
				}
				ServiceResponse response = usi.deleteGroup(selectedGroup);
				warningLabel.setForeground(response.isSuccess() ? Color.GRAY : Color.RED);
				warningLabel.setText(response.getMessage());
				groupList.clearSelection();
				selectedGroup = null; 
			}

		});
		mnMenu.add(removeGroupBtn);

		
		

		final HomePage myHomePage = this;
		groupList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				warningLabel.setText("");
				JList list = (JList) evt.getSource();
				
				selectedGroup = (Group) list.getSelectedValue();

				// The selected group has changed. Notify anyone who cares.
				myHomePage.setChanged();
				myHomePage.notifyObservers(selectedGroup);
			//	setChanged();
			//	notifyObservers(clickedTasks);

			}
		});
		
		
		JButton addTaskBtn = new JButton("Add Task");
		verticalBox_1.add(addTaskBtn);
		addTaskBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedGroup == null) {
					warningLabel.setForeground(Color.RED);
					warningLabel.setText("Select a Group first!!");
					return;
				}
				//changed here 
				setChanged();
				notifyObservers(null);
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						AddTaskPopUp frame = new AddTaskPopUp(new GroupService(selectedGroup));
						frame.setVisible(true);
						//clickedTask = new Task(); 
						
						//clickedTasks = new ArrayList<Task>(); 
						//frame.setVisible(true);
					}
				});
			}
			
		});
		
		
		
		
		JButton editTaskBtn = new JButton("Edit Task");
		verticalBox_1.add(editTaskBtn);
		editTaskBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedGroup == null) {
					warningLabel.setForeground(Color.RED);
					warningLabel.setText("Select a Group first!!");
					return;
				}
				//if(clickedTask == null || clickedTasks.size() == 0) {
				if(clickedTask == null) {
					warningLabel.setForeground(Color.RED);
					warningLabel.setText("You must pick a task to edit first!!");
					return;
				}
				myHomePage.setChanged();
				myHomePage.notifyObservers(null);
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						AddTaskPopUp popup = new AddTaskPopUp(new GroupService(selectedGroup),clickedTask);
						
						//clickedTasks = new ArrayList<>();
						clickedTask = null; 
						TasksTable.clearSelection();
						popup.setVisible(true);
					}
				});
			}
			
		});
		
		
		JButton deleteTaskBtn = new JButton("Delete Task");
		verticalBox_1.add(deleteTaskBtn);
		deleteTaskBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedGroup == null) {
					warningLabel.setForeground(Color.RED);
					warningLabel.setText("Select a Group first!!");
					return;
				}
				//if(clickedTasks == null || clickedTasks.size() == 0) {
				if(clickedTask == null) {
					warningLabel.setForeground(Color.RED);
					warningLabel.setText("You must pick task to delete first!!");
					return;
				}
				GroupServiceInterface gsi = new GroupService(selectedGroup);
				ServiceResponse response = gsi.deleteTask(clickedTask);
				warningLabel.setForeground(response.isSuccess() ? Color.GRAY : Color.RED);
				warningLabel.setText(response.getMessage());
				clickedTask = null; 
				TasksTable.clearSelection();
			}
			
		});
		
		/*
		final HomePage mySecondHomePage = this;
		taskList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JTable table = (JTable) evt.getSource();
				clickedTasks = (List<Task>) table.getSelectedRow();

				// The selected group has changed. Notify anyone who cares.
				myHomePage.setChanged();
				myHomePage.notifyObservers(clickedTasks);
			}
		});
		*/
		
		
	}
	
	
	public void setVisible(boolean visibility) {
		TimeManagementHome.setVisible(visibility);
	}

}

