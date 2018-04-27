/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: ObservantTableModel
 */

package model;

import java.util.Observer;
import javax.swing.table.TableModel;

import data.Task;

/*
 * Interface for SelectedTaskTableModel, defines how it is observed
 */
public interface ObservantTableModel<E> extends Observer, TableModel {

    public Task getObservedValue(int index);
}

