package com.task.rest.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaskList {
private String taskDesc;
private String dueDate;
private boolean completed;
private int id;

public String getTaskDesc() {
	return taskDesc;
}
public void setTaskDesc(String taskDesc) {
	this.taskDesc = taskDesc;
}
public String getDueDate() {
	return dueDate;
}
public void setDueDate(String dueDate) {
	this.dueDate = dueDate;
}
public boolean isCompleted() {
	return completed;
}
public void setCompleted(boolean completed) {
	this.completed = completed;

}


	public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


	
	public TaskList() {
	// TODO Auto-generated constructor stub
	id=-1;
    taskDesc="";
	dueDate = "";
    completed = false;     
}

public TaskList(String taskDesc, String dueDate, boolean completed,int id) {
this.taskDesc=taskDesc;
this.dueDate=dueDate;
this.completed=completed;
this.id=id;
}

}
