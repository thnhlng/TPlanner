package com.example.TPlanner.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable =false, updatable = false)
	private Long id;

	//Username
	@Column(name = "username", nullable =false, unique = true)
	private String username;
	
	@Column(name="password",nullable=false)
	private String passwordHash;
	
	@Column(name="role",nullable=false)
	private String role;
	
	@Column(name="email")
	private String email;
	
    @ManyToMany
    @JoinTable(
    		name = "team_have",
    		joinColumns = @JoinColumn(name = "id"),
    		inverseJoinColumns = @JoinColumn(name = "teamId"))
    List<Team> haveTeams;
    
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fileDB_id", referencedColumnName = "id")
//    private FileDB files;
    
    private String secret = "";
	
	   public User() {
	    }

		public User(String username, String passwordHash, String role) {
			super();
			this.username = username;
			this.passwordHash = passwordHash;
			this.role = role;
//			this.email = email;
			
		}
		
//		public User(String username, String passwordHash, String role,  List<Team> haveTeams) {
//			super();
//			this.username = username;
//			this.passwordHash = passwordHash;
//			this.role = role;
//			this.haveTeams = haveTeams;
//		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	

	public List<Team> getHaveTeams() {
		return haveTeams;
	}

	public void setListHaveTeams(List<Team> haveTeams) {
		this.haveTeams = haveTeams;
	}
	

//	public FileDB getFileDB() {
//		return files;
//	}
//
//	public void setFileDB(FileDB fileDB) {
//		this.files = fileDB;
//	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", role=" + role
				+ "File=" + "]";
	}
	
	
}
