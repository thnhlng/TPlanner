package com.example.TPlanner.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long>{
	List<Team>findByName(String name);
	
	//List<Team> findByUsers(User have);
	List<Team> findByHave(User user);
}
