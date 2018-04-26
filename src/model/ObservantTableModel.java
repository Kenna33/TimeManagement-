/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: ObservantTableModel
 */

package model;

import java.util.List;
import java.util.Observer;
import javax.swing.table.TableModel;

import data.Task;

public interface ObservantTableModel<E> extends Observer, TableModel {

    public Task getObservedValue(int index);
}

