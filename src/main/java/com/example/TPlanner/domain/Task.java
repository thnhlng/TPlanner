package com.example.TPlanner.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long taskId;
	private String taskName, taskDescription;
	private String startingDate, deadLine;
	private int daysLeft;
	private boolean isDone;
	private double cost;
	
	@ManyToOne
	@JoinColumn(name="teamId", nullable=false)
	private Team team;
	
	
	public Task() {
		super();
	}
	
	public Task(String taskName, String taskDescription, String startingDate, String deadLine, Team team, boolean isDone, double cost) {
		super();
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.startingDate = startingDate;
		this.deadLine = deadLine;
		this.team = team;
		this.isDone = isDone;
		this.cost = cost;
	}
	
	//***GETTERS AND SETTER ***
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	
	public int getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(int daysLeft) {
		this.daysLeft = daysLeft;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	//***TOSTRING***
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", startingDate=" + startingDate + ", deadLine=" + deadLine + ", daysLeft=" + daysLeft + ", isDone="
				+ isDone + ", team=" + team + "]";
	}


}
