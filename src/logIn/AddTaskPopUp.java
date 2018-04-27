/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: AddTaskPopUp
 */

package logIn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import data.Task;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import service.GroupServiceInterface;
import service.ServiceResponse;

/*
 * This class has the responsibility of the user interface 
 * pop up, listening for user input, then delegating the 
 * responsibilities of creating a task to the GroupService class
 * Implement prototype design by creating instance of GroupService 
 * in constructor
 */
public class AddTaskPopUp extends JFrame {

    /**
     * default serialized number
     */
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField name;
    private JTextField description;


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

        String[] priorityStrings = {"High", "Medium", "Low"};
        JComboBox priorityList = new JComboBox(priorityStrings);
        priorityList.setSelectedIndex(2);

        String[] progressStrings = {"Finished", "Started", "Not Started"};
        JComboBox progressList = new JComboBox(progressStrings);
        progressList.setSelectedIndex(2);
		

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

        name = new JTextField(15);
        description = new JTextField(40);

        GridBagConstraints right = new GridBagConstraints();
        right.anchor = GridBagConstraints.WEST;
        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.EAST;

        right.weightx = (int) 2;
        right.fill = GridBagConstraints.REMAINDER;
        right.gridwidth = GridBagConstraints.REMAINDER;

        panel.add(taskLabel, left);
        panel.add(name, right);
        panel.add(descripLabel, left);
        panel.add(description, right);
        panel.add(priorityLabel, left);
        panel.add(priorityList, right);
        panel.add(progressLabel, left);
        panel.add(progressList, right);
        panel.add(dateLabel, left);
        panel.add(datePicker, right);
       
        JSeparator separator = new JSeparator();
        panel.add(separator);

        name.setText(taskInQuestion.getName());
        description.setText(taskInQuestion.getDescription());
       
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

            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {

                // update the name with the current text field value
                taskInQuestion.setName(name.getText());
                taskInQuestion.setDescription(description.getText());
                taskInQuestion.setDueDate(
                        new Date((datePicker.getModel().getYear() - 1900),
                                datePicker.getModel().getMonth(), datePicker.getModel().getDay()));
                taskInQuestion.setPriority(priorityList.getSelectedIndex());
                taskInQuestion.setProgress(progressList.getSelectedIndex());

                // save the new group
                ServiceResponse response = gsi.savetask(taskInQuestion);

                if (response.isSuccess()) {
                    window.dispose();
                }

                // something went wrong with saving
                warningLabel.setText(response.getMessage());

            }

        });
    }

}


