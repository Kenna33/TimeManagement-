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
	private List<Task> clickedTasks;

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
		verticalBox.add(groupList);

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
		
		TasksTable = new JTable(selectedTasks);
		TasksTable.setFillsViewportHeight(true);
		TasksTable.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent me) {
				JTable table = (JTable) me.getSource();
				ObservantTableModel<List<Task>> taskModel = (ObservantTableModel<List<Task>>)table.getModel();
				int[] selected = table.getSelectedRows();
				final List<Task> allSelectedTasks = taskModel.getObservedValue();
				clickedTasks = new ArrayList<Task>();
				for(Integer taskIndex: selected) {
					clickedTasks.add(allSelectedTasks.get(taskIndex));
				}
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

		JMenuItem addFarmerBtn = new JMenuItem("Add Group");
		addFarmerBtn.addActionListener(new ActionListener() {
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
		mnMenu.add(addFarmerBtn);
		
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
			}

		});
		mnMenu.add(removeGroupBtn);

		
		

		final HomePage myHomePage = this;
		groupList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				selectedGroup = (Group) list.getSelectedValue();

				// The selected farmer has changed. Notify anyone who cares.
				myHomePage.setChanged();
				myHomePage.notifyObservers(selectedGroup);

			}
		});
		
		/*
		JButton transferBtn = new JButton("Transfer Cow");
		verticalBox_1.add(transferBtn);
		transferBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(clickedCows == null || clickedCows.size() == 0) {
					warningLabel.setForeground(Color.RED);
					warningLabel.setText("You must pick cows to transfer first!!");
					return;
				}
				myWindow.setChanged();
				myWindow.notifyObservers(null);
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						TransferCowPopUp popup = new TransferCowPopUp(lm, fsi, clickedCows);
						clickedCows = new ArrayList<>();
						popup.setVisible(true);
					}
				});
			}
			
		});
		*/
	}
	
	
	public void setVisible(boolean visibility) {
		TimeManagementHome.setVisible(visibility);
	}

}

