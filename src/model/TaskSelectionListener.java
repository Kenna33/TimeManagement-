/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: TaskSelectionListener
 */
package model;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TaskSelectionListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.print(e.getFirstIndex());
    }
}
