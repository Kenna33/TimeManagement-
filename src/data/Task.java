package data;

import java.sql.Date;

public class Task {

	//priority 
	private final static int LOW = 1;
	private final static int MEDIUM = 2; 
	private final static int HIGH = 3; 
	
	//progress
	private final static int NOT_STARTED = 1; 
	private final static int STARTED = 2; 
	private final static int FINISHED = 3; 
	
	private Integer taskID; 
	private String name; 
	private Date dueDate; 
	private int priority; 
	private int progress; 
	private String description; 
	private Integer groupID; 
	private Integer userID;
	
	public Integer getTaskID() {
		return taskID;
	}
	public void setTaskID(Integer taskID) {
		this.taskID = taskID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public Integer getGroupID() {
		return groupID;
	}
	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	} 
	
}
