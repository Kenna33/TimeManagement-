package logIn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import data.Group;
import data.Task;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import service.GroupServiceInterface;
import service.ServiceResponse;
import service.UserService;
import service.UserServiceInterface;

public class AddTaskPopUp extends JFrame{

	private JPanel contentPane;
	
	private JTextField name; 
	private JTextField dueDate; 
	private JTextField priority; 
	private JTextField progress; 
	private JTextField description; 
	
	
	

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTaskPopUp frame = new AddTaskPopUp(new GroupService(checkLogin.getID()));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	public AddTaskPopUp(final GroupServiceInterface gsi) {
		this(gsi, null);
	}

	public AddTaskPopUp(final GroupServiceInterface gsi, Task oldTask) {
		
		// Retain reference to this under different name
		final AddTaskPopUp window = this;
		
		// Make a group or reuse a group
		Task taskInQuestion = oldTask == null ? new Task() : oldTask;
		
		setTitle(oldTask == null ? "Add new task" : "Edit Task");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 700, 900, 900);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(verticalBox, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		verticalBox.add(panel);
		
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
		
		/*
		//we choose the wanted date pattern
		DateFormatter dateFormatter=new DateFormatter(new SimpleDateFormat("yyyy-MM-dd"));
		//the javadoc new DefaultFormatterFactory(defaultFormat, displayFormat, editFormat)
		DefaultFormatterFactory dateFormatterFactory =new DefaultFormatterFactory(dateFormatter,
		 new DateFormatter(),
		 dateFormatter
		 );
		JFormattedTextField dateField = new JFormattedTextField(dateFormatterFactory,15);
		*/
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

		//add(datePicker);
		
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
		panel.add(datePicker, right);
		 
		//initialize with now
		
		
		JSeparator separator = new JSeparator();
		panel.add(separator);
	
		name.setText(taskInQuestion.getName());
		description.setText(taskInQuestion.getDescription());
		//dateField.setValue(taskInQuestion.getDueDate());
		
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
				taskInQuestion.setName(name.getText());
				taskInQuestion.setDescription(description.getText());
				//taskInQuestion.setDueDate(new (Date(datePicker.getModel().getDay());
				taskInQuestion.setDueDate(
						new Date((datePicker.getModel().getYear() - 1900),
								datePicker.getModel().getMonth(),datePicker.getModel().getDay()));
				//taskInQuestion.setDueDate((Date) dateField.getValue());
				taskInQuestion.setPriority(priorityList.getSelectedIndex()); 
				taskInQuestion.setProgress(progressList.getSelectedIndex());
				
				// save the new group
				ServiceResponse response = gsi.savetask(taskInQuestion);
				
				if(response.isSuccess()) {
					// dispose of the window
					window.dispose();
				}
				
				// something went wrong with saving
				warningLabel.setText(response.getMessage());
				
			}
			
		});
	}

}


