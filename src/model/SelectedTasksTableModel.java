/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: SelectedTasksTableModel
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.table.AbstractTableModel;

import data.Group;
import data.Task;

public class SelectedTasksTableModel extends AbstractTableModel implements ObservantTableModel<List<Task>> {

    /**
	 * defaul serial number
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] taskFields = {"Name", "Description", "Priority", "Progress", "Due Date"};

    private List<Task> selectedTasks = new ArrayList<Task>();


    @Override
    public void update(Observable o, Object arg) {

        if (arg != null) {
            setSelectedTasks(((Group) arg).getTaskList());
        } else {
            setSelectedTasks(new ArrayList<Task>());
        }

    }

    @Override
    public int getColumnCount() {
        return taskFields.length;
    }

    @Override
    public int getRowCount() {
        return selectedTasks.size();
    }

    public String getColumnName(int column) {
        return taskFields[column];

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String field = taskFields[columnIndex];
        if (field == "Name") {
            return selectedTasks.get(rowIndex).getName();
        }
        if (field == "Description") {
            return selectedTasks.get(rowIndex).getDescription();
        }
        if (field == "Priority") {
            if (selectedTasks.get(rowIndex).getPriority() == 0) {
                return "High";
            } else if (selectedTasks.get(rowIndex).getPriority() == 1) {
                return "Medium";
            } else {
                return "Low";
            }
        }
        if (field == "Progress") {
            if (selectedTasks.get(rowIndex).getProgress() == 0) {
                return "Finished";
            } else if (selectedTasks.get(rowIndex).getProgress() == 1) {
                return "Started";
            } else {
                return "Not Started";
            }
        }
        if (field == "Due Date") {
            return selectedTasks.get(rowIndex).getDueDate();
        }
        return null;
    }


    private void setSelectedTasks(List<Task> selectedTasks) {
        this.selectedTasks = selectedTasks;
        this.fireTableDataChanged();
    }

    @Override
    public Task getObservedValue(int index) {

        return selectedTasks.get(index);
    }


}
