package warehouses.project.ville;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class VilleServices {

	private static List<Ville> ville = new ArrayList<>();
	private static int CitiesCount = 3;
	static {
		
		
	}
	
	//show all cities
	public List<Ville> ShowAll() {
		return ville;
		
	}
	
	//show one cities
	public Ville ShowOne(int id){
		for (Ville city:ville) {
			if (city.getID_Ville()==id)
				return city;
				}
		
	return null;
	 }
	
	//add a city
	public Ville Add(Ville city) {
		if (city.getID_Ville()== 0) {
			city.setID_Ville(++CitiesCount);
		}
		ville.add(city);
		return city;
	}
	
			
//delete a city
	public Ville Delete(int id) {
		Iterator<Ville> iterator= ville.iterator();
		while(iterator.hasNext()) {
			  Ville city= iterator.next();
			  if (city.getID_Ville()== id) {
				  iterator.remove();
				  return city;
			  }
		  }
		  return null;
		  }
		
	}
	
	
	

