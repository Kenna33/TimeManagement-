package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


import data.Group;
import data.Task;

public class SelectedTasksTableModel extends AbstractTableModel implements ObservantTableModel<List<Task>>{
	
	private static final String[] taskFields = {"Name", "Description", "Priority", "Progress", "Due Date"};
	
	private List<Task> selectedTasks = new ArrayList<Task>();

	
	
	@Override
	public void update(Observable o, Object arg) {
		
		if(arg != null) {
			setSelectedTasks(((Group)arg).getTaskList());
		}
		else {
			setSelectedTasks(new ArrayList<Task>());
		}
		
		/*
		Map<String, Integer> changes = (Map<String, Integer>) arg;
		if(changes.containsKey("new")) {
			this.fireTableDataChanged(this, changes.get("new"), changes.get("new"));
		}
		if(changes.containsKey("remove")) {
			fireIntervalRemoved(this, changes.get("remove"), changes.get("remove"));
		}
		*/
		
	}

	@Override
	public int getColumnCount() {
		return taskFields.length;
	}

	@Override
	public int getRowCount() {
		return selectedTasks.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String field = taskFields[columnIndex];
		if(field == "Name") {
			return selectedTasks.get(rowIndex).getName();
		}
		if(field == "Description") {
			return selectedTasks.get(rowIndex).getDescription();
		}
		if(field == "Priority") {
			return selectedTasks.get(rowIndex).getPriority();
		}
		if(field == "Progress") {
			return selectedTasks.get(rowIndex).getProgress();
		}
		if(field == "Due Date") {
			return selectedTasks.get(rowIndex).getDueDate();
		}
		return null;
	}

	
	private void setSelectedTasks(List<Task> selectedTasks) {
		this.selectedTasks = selectedTasks;
		this.fireTableDataChanged();
	}

	@Override
	public List<Task> getObservedValue() {

		return new ArrayList<Task>(selectedTasks);
	}
	
}
