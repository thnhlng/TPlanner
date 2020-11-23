package com.example.TPlanner.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;



public interface TaskRepository extends CrudRepository<Task, Long>{

	//Optional<Task> findByTeam(Team team);
	//List<Task> findByTeams(Team team);
	Iterable<Task> findByTeam(Team team);
	//Iterable<Task> findAllTeam1(Team team);
}
