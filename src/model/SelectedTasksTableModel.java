package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import data.Task;

public class SelectedTasksTableModel extends AbstractTableModel implements ObservantTableModel<Task>{
	
	private static final String[] taskFields = {"Name", "Description", "Priority", "Progress", "Due Date"};
	
	private List<Task> selectedTasks = new ArrayList<Task>();

	public SelectedTasksTableModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task getObservedValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
