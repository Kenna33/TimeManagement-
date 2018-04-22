package logIn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import data.Task;
import service.GroupServiceInterface;
import service.ServiceResponse;

public class TaskPopUp implements ActionListener{

	private JFrame  frame;
	private JPanel  panel;
	private JTextField name; 
	private JTextField dueDate; 
	private JTextField priority; 
	private JTextField progress; 
	private JTextField description; 
	private JLabel  warningLabel;
	private JLabel  success; 
	private Task task; 
	
	public TaskPopUp(final GroupServiceInterface gsi) {
		this(gsi, null);
	}
	
	public TaskPopUp(final GroupServiceInterface gsi, Task oldTask) {
		/*
		// Retain reference to this under different name
		//	final AddTaskPopUp window = this;
		
		// Make a group or reuse a group
		Task taskInQuestion = oldTask == null ? new Task() : oldTask;
		panel = new JPanel();
        panel.setLayout(new GridBagLayout());
		
		//setTitle(oldTask == null ? "Add new task" : "Edit Task");
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setResizable(true); 
		//setBounds(700, 700, 900, 900);
		//contentPane = new JPanel();
		//contentPane.setBorder(null);
		//setContentPane(contentPane);
		//contentPane.setLayout(new BorderLayout(0, 0));
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(verticalBox, BorderLayout.CENTER);
		
		//JPanel panel = new JPanel(new BorderLayout());
		//verticalBox.add(panel);
		
		
       // panel.setBackground(Color.GRAY);

        frame = new JFrame();
        frame.setTitle(oldTask == null ? "Add new task" : "Edit Task");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
		
		JLabel taskLabel = new JLabel("Name");
		JLabel descripLabel = new JLabel("Description"); 
		JLabel priorityLabel = new JLabel("Priority"); 
		JLabel progressLabel = new JLabel("Progress"); 
		JLabel dateLabel = new JLabel("Date"); 
		
		String[] priorityStrings = { "High", "Medium", "Low"};
		JComboBox priorityList = new JComboBox(priorityStrings);
		priorityList.setSelectedIndex(2);
		//priorityList.addActionListener(this);
		
		String[] progressStrings = { "Finished", "Started", "Not Started"};
		JComboBox progressList = new JComboBox(progressStrings);
		progressList.setSelectedIndex(2);
		//priorityList.addActionListener(this);
		
		//we choose the wanted date pattern
		DateFormatter dateFormatter=new DateFormatter(new SimpleDateFormat("yyyy-MM-dd"));
		//the javadoc new DefaultFormatterFactory(defaultFormat, displayFormat, editFormat)
		DefaultFormatterFactory dateFormatterFactory =new DefaultFormatterFactory(dateFormatter,
		 new DateFormatter(),
		 dateFormatter
		 );
		JFormattedTextField dateField = new JFormattedTextField(dateFormatterFactory);
		
		name = new JTextField(15);
		description = new JTextField(40);
		
		GridBagConstraints right = new GridBagConstraints();
        right.anchor = GridBagConstraints.WEST;
        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.EAST;

        right.weightx = (int) 2;
        right.fill = GridBagConstraints.REMAINDER;
        right.gridwidth = GridBagConstraints.REMAINDER;
		
		panel.add(taskLabel,left);
		panel.add(name,right);
		panel.add(descripLabel,left); 
		panel.add(description,right);
		panel.add(priorityLabel,left);
		panel.add(priorityList,right);
		panel.add(progressLabel,left);
		panel.add(progressList,right); 
		panel.add(dateLabel,left);
		panel.add(dateField, right);
		 
		//initialize with now
		
		
		//JSeparator separator = new JSeparator();
		//panel.add(separator);
	
		taskLabel.setText(taskInQuestion.getName());
		descripLabel.setText(taskInQuestion.getDescription());
		dateField.setValue(taskInQuestion.getDueDate());
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalBox.add(verticalGlue);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setHorizontalAlignment(SwingConstants.LEFT);
		horizontalBox.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
			
		});
		
		Component horizontalGlue = Box.createHorizontalGlue();
		//horizontalBox.add(horizontalGlue);
		
		JButton saveBtn = new JButton("Save");
		saveBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		//horizontalBox.add(saveBtn);
		
		final JLabel warningLabel = new JLabel("");
		warningLabel.setForeground(Color.RED);
		//contentPane.add(warningLabel, BorderLayout.NORTH);
		saveBtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		taskInQuestion.setName(taskLabel.getText());
		taskInQuestion.setDescription(descripLabel.getText());
		taskInQuestion.setDueDate((Date) dateField.getValue());
		taskInQuestion.setPriority(priorityList.getSelectedIndex()); 
		taskInQuestion.setProgress(progressList.getSelectedIndex());
		
		// save the new group
		ServiceResponse response = gsi.savetask(taskInQuestion);
		
		if(response.isSuccess()) {
			// dispose of the window
			frame.dispose();
		}
		
		// something went wrong with saving
		warningLabel.setText(response.getMessage());
	}
	*/
	
		


