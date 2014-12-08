package src.main.java.com.github.hsmrs_gui.project.model;

public class Robot {

	private String name;
	private Task assignedTask;
		
	public Robot(){
		name = "No name given";
		assignedTask = new Task();
	}
	
	public Robot (String name){
		this.name = name;
		assignedTask = new Task();
	}
	
	public Robot (String name, Task task){
		this.name = name;
		assignedTask = task;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Task getAssignedTask() {
		return assignedTask;
	}

	public void setAssignedTask(Task assignedTask) {
		this.assignedTask = assignedTask;
	}
	
	
}
