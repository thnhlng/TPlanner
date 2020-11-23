package com.example.TPlanner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.TPlanner.domain.Task;
import com.example.TPlanner.domain.TaskRepository;
import com.example.TPlanner.domain.Team;
import com.example.TPlanner.domain.TeamRepository;
import com.example.TPlanner.domain.User;
import com.example.TPlanner.domain.UserRepository;




@SpringBootApplication
public class TPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TPlannerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(TeamRepository tRepository, TaskRepository tARepository, UserRepository uRepository) {
		return (args) -> {
			uRepository.deleteAll();
		//	pRepository.deleteAll();
//			tRepository.deleteAll();
//			tARepository.deleteAll();
			
//			cRepository.save(new Category("Classic"));
//			cRepository.save(new Category("Novel"));
//			cRepository.save(new Category("Fantasy"));
//			urepository.deleteAll();
//			Team team = new Team("NameTeam", "Family", "10.11.2020");
//			Task t = new Task("taskName", "taskDescription", "startingDate", "21.11.2020", team, false, 0);
//			Task t2 = new Task("taskName2", "2taskDescription", "2startingDate", "10.11.2020", team, false, 0);
//			List <Task> ta = new ArrayList<>();
//			ta.add(t);
//			tRepository.save(team);
//			tARepository.save(t);
//			tARepository.save(t2);
		
			
//			Book b1 = new Book("Sapiens", "Yuval Noah Harari", 2011, "22223", 20.00, cRepository.findByName("Classic").get(0));
//			Book b2 = new Book("Homo Deus", "Yuval Noah Harari", 2015, "26537", 25.00, cRepository.findByName("Novel").get(0));
//			repository.save(b1);
//			repository.save(b2);
			
			
			//password1 / password2
			User user1 = new User("user1","$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6","USER");
			User user2 = new User("admin1","$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C","ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);
//			
//			

		};
	}
	
//	@Bean
//	Path path(){
//		return Paths.get(System.getProperty("java.io.tmpdir"));
//	}

}
