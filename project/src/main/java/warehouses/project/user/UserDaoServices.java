package warehouses.project.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoServices {
	
	private static List<Users> users = new ArrayList<>();
	
	
	static {
		
	}
	public List<Users> findAll(){
		return users;
	}
  public Users save(Users user) {
	  users.add(user);
	  return user;
  }
  public Users findOne(int id) {
	  for (Users user:users) {
		  if (user.getId_User()==id) {
			  return user;
		  }
	  }
	  return null;
  }
    public Users deleteById(int id){
	  Iterator<Users> iterator = users.iterator();
	  while(iterator.hasNext()) {
		  Users user= iterator.next();
		  if (user.getId_User()== id) {
			  iterator.remove();
			  return user;
		  }
	  }
	  return null;
	  }
}
