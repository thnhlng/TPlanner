package com.example.TPlanner.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TEAM")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long teamId;
	
	private String name, description, startingDate, startingTime;
	private double cost;
	
	//private User adminUser;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="team")
	private List<Task> tasks;
	
	@ManyToMany(mappedBy = "haveTeams")
	List<User> have;
	
	public Team() {}
		
	public Team(String name, String description, String startingDate) {
		super();
		this.name = name;
		this.description = description;
		this.startingDate = startingDate;
		//this.adminUser = adminUser;
	}
	
	
	//***TOSTRING***
	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", name=" + name + ", description=" + description +  "]";
	}	
	
	
	//***GETTERS AND SETTERS***


	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamid) {
		this.teamId = teamid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public String getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<User> getHave() {
		return have;
	}

	public void setHave(List<User> have) {
		this.have = have;
	}
	
	
	
	
	
//	public Task getTask() {
//		return this.task;
//	}
//	
//	public void setTask(Task task) {
//		this.task = task;
//	}
	
//	public User getAdminUser() {
//		return adminUser;
//	}
//	public void setAdminUser(User adminUser) {
//		this.adminUser = adminUser;
//	}
//	
	
}
