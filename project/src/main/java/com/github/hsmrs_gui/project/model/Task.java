package src.main.java.com.github.hsmrs_gui.project.model;

import java.util.ArrayList;
import java.util.List;

public class Task {

	private String name;
	private List<Robot> owners;
	private String status;
	
	public Task(){
		name = "Idle";
		owners = new ArrayList<Robot>();
		status = "---";
	}
	
	public Task(String name){
		this.name = name;
		owners = new ArrayList<Robot>();
		status = "Not claimed";
	}
	
	public Task(String name, List<Robot> owners, String status){
		this.name = name;
		this.owners = owners;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Robot> getOwners() {
		return owners;
	}

	public void addOwner(Robot owner) {
		owners.add(owner);
	}
	
	public void removeOwner(Robot owner){
		owners.remove(owner);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString(){
		return name;
	}
}
