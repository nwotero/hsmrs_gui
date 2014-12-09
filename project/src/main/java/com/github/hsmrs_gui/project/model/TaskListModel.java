package src.main.java.com.github.hsmrs_gui.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class TaskListModel extends AbstractListModel{
	private static TaskListModel instance;
	private List<Task> taskList;
	
	private TaskListModel()
	{
		taskList = new ArrayList<Task>();
		
	}
	
	static public synchronized TaskListModel getTaskListModel() {
		if (instance == null)
			instance = new TaskListModel();
		return instance;
	}
	
	public void addTask(Task newTask){
		taskList.add(newTask);
	}
	
	public Task removeTask(Task targetTask){
		taskList.remove(targetTask);
		return targetTask;
	}
	
	public Task removeTask(int index){
		Task targetTask = getElementAt(index);
		taskList.remove(index);
		return targetTask;
	}
	
	public Task getElementAt(int index) {
		return taskList.get(index);
	}

	public int getSize() {
		return taskList.size();
	}
}
