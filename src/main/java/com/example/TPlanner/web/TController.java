package com.example.TPlanner.web;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.TPlanner.domain.SignupForm;
import com.example.TPlanner.domain.Task;
import com.example.TPlanner.domain.TaskRepository;
import com.example.TPlanner.domain.Team;
import com.example.TPlanner.domain.TeamRepository;
import com.example.TPlanner.domain.User;
import com.example.TPlanner.domain.UserRepository;

@Controller
public class TController {
	//***REPOSITORIES***
	@Autowired
	private TeamRepository repository;
	
	@Autowired
	private UserRepository uRepository;
	
	@Autowired
	private TaskRepository tRepository;
	
	
//	@RequestMapping(value="/")
//	public String mainPage() {
//		return "login";
//	}
	
	@RequestMapping(value="/test1")
	public String test1() {
		return "uploadFile";
	}
	
	@RequestMapping(value="/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping(value="/projects")
	public String projectList(Model model, Principal principal) {
		User user = uRepository.findByUsername(principal.getName());
		System.out.println("####LINE 66: " + repository.findByHave(user));
		model.addAttribute("teams", repository.findByHave(user));
		//model.addAttribute("teams", repository.findAll());
		System.out.println("USER:Line64 " + uRepository.findAll());
		
			return "projects";
	}
	
	private Long teamId;
	private Long userId;
	
	//getTeam
	 @RequestMapping(value = "/team/{id}", method = RequestMethod.GET)
	 public String viewTeam(@PathVariable("id") Long teamId, Model model) {
		//Find a certain team
		Optional<Team> team = repository.findById(teamId);
	    model.addAttribute("teams", team.get());
	    this.teamId = teamId;
	    //Find tasks belonged to team
		Iterable<Task> temp = this.tRepository.findByTeam(team.get());	
		List<Task> target = new ArrayList<>();
		temp.forEach(target::add);
				
		Iterable<Task> tasks = target;
		LocalDate now = LocalDate.now();  
		   for (Task s : tasks) {
			  //				Date date1=new SimpleDateFormat("dd.MM.yyyy").parse(s.getDeadLine());
							  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
							  String deadL = s.getDeadLine();
							  LocalDate deadline = LocalDate.parse(deadL, formatter);
//							  System.out.println("Deadline Date: " + deadline);
							  long daysBetween = ChronoUnit.DAYS.between(now, deadline);
//								System.out.println("DAYS BETWEEN: " + daysBetween);
								s.setDaysLeft((int) daysBetween);
//								System.out.println("DAYS LEFT: " + s.getDaysLeft());
    }
		
		model.addAttribute("tasks", tasks);
		if(team.get().getTasks() == null) {
			System.out.println("ITS NULL");
		}else {
			System.out.println("LIST: " + team.get().getTasks());	
		}
		 return "team";
	 }

	
	//add Team
	@RequestMapping(value = "/add")
	public String addProject(Model model, Principal principal) {
		model.addAttribute("team", new Team());
		String userName = principal.getName();
		User user = this.uRepository.findByUsername(userName);
		System.out.println("USER ON LINE 113: " + user);
		model.addAttribute("user", user);
		return "addProject";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Team team, Principal principal) {
		String userName = principal.getName();
		User user = this.uRepository.findByUsername(userName);
		List<User> userList = new ArrayList<>();
		
		userList.add(user);
		team.setHave(userList);
		
		if(user.getHaveTeams() == null) {
			user.setListHaveTeams(new ArrayList<>());
			user.getHaveTeams().add(team);
		}else {
			user.getHaveTeams().add(team);
		}

		System.out.println("***LINE 133: " + team.getHave());
		System.out.println("***LINE 138: " + user.getHaveTeams());
		//uRepository.save(user);
		repository.save(team);
		return "redirect:projects";
	}
	
	//update Team
	 @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	    public String modifyTeam(@PathVariable("id") Long teamId, Model model) {
	    	Optional<Team> team = repository.findById(teamId);
	    	model.addAttribute("team", team);
	    	 return "editTeam";
	    } 
	       
	
	//delete Team
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('Admin')")
	public String deleteTeam(@PathVariable("id") Long teamId, Model model) {
		repository.deleteById(teamId);
		return "redirect:../projects";
	}
	
	 
	//***TASK***
	
	//get Task
		@RequestMapping(value= "/task/{id}")
		public String getTask(@PathVariable("id") Long taskId, Model model) {
			Optional<Task> tasks = tRepository.findById(taskId);
			
			LocalDate now = LocalDate.now();  
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
			  String deadL = tasks.get().getDeadLine();
			  LocalDate deadline = LocalDate.parse(deadL, formatter);
			  System.out.println("Deadline Date: " + deadline);
			  long daysBetween = ChronoUnit.DAYS.between(now, deadline);
				System.out.println("DAYS BETWEEN: " + daysBetween);
				tasks.get().setDaysLeft((int) daysBetween);
				System.out.println("DAYS LEFT: " + tasks.get().getDaysLeft());

		
			System.out.println("SPECIFIC : " + tasks.get().getDaysLeft());
				model.addAttribute("tasks", tasks.get());
				return "task";
		}
	
	//add Task
		@RequestMapping(value = "/addTask/{id}", method = RequestMethod.GET)
		public String addTask(@PathVariable("id") Long teamId, Model model) {
			Optional<Team> team = repository.findById(teamId);
			model.addAttribute("teams", team.get());
			model.addAttribute("task", new Task());
			return "addTask";
		}
	
		@RequestMapping(value = "/addTask/saveTask", method = RequestMethod.POST)
		public String saveTask(Task task) {
			System.out.println("BEFORE SAVE: " + task);
			tRepository.save(task);
			String toTeam = "redirect:/team/" + this.teamId;
			return toTeam;
		}
		
	//edit Task
		 @RequestMapping(value = "/task/edit/{id}", method = RequestMethod.GET)
		    public String editTask(@PathVariable("id") Long taskId, Model model) {
		    	Optional<Task> tasks = tRepository.findById(taskId);
		    	System.out.println("TEAM ID: " + teamId);
		    	Optional<Team> team = repository.findById(teamId);
		    	model.addAttribute("task", tasks);
				model.addAttribute("teams", team.get());
		    	 return "editTask";
		    } 
	
		 @RequestMapping(value = "/task/saveTask", method = RequestMethod.POST)
			public String editedTask(Task task) {
				System.out.println("BEFORE SAVE EDITS: " + task);
				tRepository.save(task);
				String toTeam = "redirect:/team/" + this.teamId;
				return toTeam;
			}
		 
	//delete Task
		@RequestMapping(value="/delete/task/{id}", method = RequestMethod.GET)
		public String deleteTask(@PathVariable("id") Long taskId, Model model) {
			tRepository.deleteById(taskId);
			String toTeam = "redirect:/team/" + this.teamId;
			return toTeam;
		}
		
	//add taskcost	
		 @RequestMapping(value = "/task/cost/{id}", method = RequestMethod.GET)
		    public String addCost(@PathVariable("id") Long taskId, Model model) {
		    	Optional<Task> tasks = tRepository.findById(taskId);
		    	System.out.println("TAASK: " + tasks);
		    	System.out.println("TEAM ID: " + teamId);
		    	Optional<Team> team = repository.findById(teamId);
		    	model.addAttribute("task", tasks.get());
				model.addAttribute("teams", team.get());
		    	 return "addcost";
		    } 
		 
		 
		@RequestMapping(value = "/ta", method = RequestMethod.GET)
		   @ResponseBody
		   public String currentUserName(Principal principal) {
			
		       return principal.getName();
		   }
	
	//*** LOGIN***	
	//login
		  @RequestMapping(value="/login")
		    public String login() {			  
		    	return "login";
		    }
		  
	//***SIGN UP***
		  
			
		    @RequestMapping(value = "signup")
		    public String addStudent(Model model){
		    	model.addAttribute("signupform", new SignupForm());
		        return "signup";
		    }	
		    
		    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
		    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		    	if (!bindingResult.hasErrors()) { // validation errors
		    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
			    		String pwd = signupForm.getPassword();
				    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				    	String hashPwd = bc.encode(pwd);
			
				    	User newUser = new User();
				    	newUser.setPasswordHash(hashPwd);
				    	newUser.setUsername(signupForm.getUsername());
				    	newUser.setEmail(signupForm.getEmail());
				    	newUser.setRole("USER");
				    	if (uRepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
				    		System.out.println("USERREPO EMAIL: " + uRepository.findByEmail(signupForm.getEmail()));
				    		if (uRepository.findByEmail(signupForm.getEmail()) == null) { // Check if email exists
					    		
				    			uRepository.save(newUser);
				    			System.out.println("***ID:***: " + newUser.getId());
				    			this.userId = newUser.getId();
					    	}
					    	else {
				    			bindingResult.rejectValue("email", "err.email", "Email already exists");    	
				    			return "signup";		    		
					    	}
				    	}
				    	else {
			    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
			    			return "signup";		    		
				    	}
				    	
		    		}
		    		else {
		    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
		    			return "signup";
		    		}
		    	}
		    	else {
		    		return "signup";
		    	}
		    	return "redirect:/login";    	
		    } 
		    
	//***FILEDBCONTROLLER***
		    
		    
}
