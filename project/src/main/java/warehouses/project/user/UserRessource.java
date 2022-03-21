package warehouses.project.user;


import java.net.URI;
//import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class UserRessource {

	@Autowired
	private UserRepository userRepository;
	
	   @GetMapping("/Home")
	     public String viewHomePage(Model model) {
	         List<Users> Userslist = userRepository.findAll();
	         model.addAttribute("Userslist", Userslist);
	         System.out.print("Get /Home "); 
	         return "User";
	     }
	
	//retrieveAllUsers
	@GetMapping("/Users")
	public List<Users> retrieveAllUsers(){
		return userRepository.findAll();
		}
	@GetMapping("/findUser/{id}")
	public String retrieveUser(@PathVariable  int id, Model model) {
			Optional<Users> User=  userRepository.findById(id);	
		if (!User.isPresent()) 
			throw new UserNotFoundException("id: "+ id);	
		 model.addAttribute("User",User.get());
			
	         return "userById";
		}
		
	@GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("User", new Users());
        return "SaveUser";
    }
		
	
	@PostMapping("/addUser")
	   public String saveUser(@ModelAttribute("User") Users User) {
		
		userRepository.save(User);
		
		return "redirect:/Home";
    }
	
	
	
	
	
	/*
	
	@PostMapping("/new")
	public ResponseEntity<Object> creatUser(@RequestBody Users user) {
		Users saveUser = userRepository.save(user);
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId_User()).toUri();
		return ResponseEntity.created(location).build();
		
	}*/
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
	    Users user =  userRepository.findById(id)
	    		.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("user", user);
	    return "editUser";
	}
	
	
	@PostMapping("/update/{id}")
	
public String updateUser(@PathVariable("id") Integer id, @Valid Users user, 
			  BindingResult result, Model model) {
			 	    if (result.hasErrors()) {
			 	    	System.out.println("ERROR in user Form");
			 	    	 return "editUser";
	      //  user.setId_User(id);     
		    
			 	    }	       
			 	 //  userRepository.save(user);
			 	    return "redirect:/Home" + userRepository.save(user);
	}	
	
	/*public Users updateUser(@PathVariable("id") Integer id, @Valid Users user, 
	 Model model) {


return userRepository.findById(id)
	      .map(users -> {
	    	  users.setFullName(user.getFullName());
	    	  users.setPhoneNumber(user.getPhoneNumber());
	    	  users.setEmail(user.getEmail());
	    	  users.setRole(user.getRole());
	    	  users.setId_User(user.getId_User());
	    	  users.setID_entreprise(user.getID_entreprise());
	        return userRepository.save(users);
	      })
	      .orElseGet(() -> {
	    	  user.setId_User(id);
	        return userRepository.save(user);
	      }
	      
	    		  );

	  }*/
	
	
	
	@GetMapping("/delete/{id}")
	public String  deleteUser(@PathVariable int id) {		
		userRepository.deleteById(id);
		return "redirect:/Home";
		
	
}
}