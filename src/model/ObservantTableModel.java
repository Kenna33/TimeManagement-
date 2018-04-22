package model;

import java.util.List;
import java.util.Observer;
import javax.swing.table.TableModel;

import data.Task;

public interface ObservantTableModel<E> extends Observer, TableModel{
	public List<Task> getObservedValue();
}

