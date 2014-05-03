package com.task.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class ListTasks {
	private List<TaskList> list;
	
	public List<TaskList> getList() {
		return list;
	}
	public void setList(List<TaskList> list) {
		this.list = list;
	}
}
