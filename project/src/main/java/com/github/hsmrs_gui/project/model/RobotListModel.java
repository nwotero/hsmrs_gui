package src.main.java.com.github.hsmrs_gui.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class RobotListModel extends AbstractListModel{

	private static RobotListModel instance;
	private List<Robot> robotList;
	
	private RobotListModel()
	{
		robotList = new ArrayList<Robot>();
		
	}
	
	static public synchronized RobotListModel getRobotListModel() {
		if (instance == null)
			instance = new RobotListModel();
		return instance;
	}
	
	public void addRobot(Robot newRobot){
		robotList.add(newRobot);
		int index = robotList.size() - 1;
		this.fireIntervalAdded(this, index, index);
	}
	
	public Robot removeRobot(Robot targetRobot){
		int index = robotList.indexOf(targetRobot);
		robotList.remove(targetRobot);
		this.fireIntervalRemoved(this, index, index);
		return targetRobot;
	}
	
	public Robot removeRobot(int index){
		Robot targetRobot = getElementAt(index);
		robotList.remove(index);
		this.fireIntervalRemoved(this, 0, 0);
		return targetRobot;
	}
	
	public Robot getElementAt(int index) {
		return robotList.get(index);
	}

	public int getSize() {
		return robotList.size();
	}
	
	public List<String> getRobotNames(){
		ArrayList<String> robotNames = new ArrayList<String>(robotList.size());
		for (Robot robot : robotList){
			robotNames.add(robot.getName());
		}
		return robotNames;
	}
	
}
