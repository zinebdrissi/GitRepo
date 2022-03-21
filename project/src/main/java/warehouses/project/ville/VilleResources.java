package warehouses.project.ville;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VilleResources {

	@Autowired
	private VilleRepository villeRepository;
	
	//retrieveAllcities
	@GetMapping("/Cities")
	public List<Ville> retrieveAllcities(){
	  return  villeRepository.findAll();	
			}
	//retrieveOneCities
@GetMapping("/Cities/{id}")
public Ville retrieveOneCity(@PathVariable int id) {
	Optional<Ville> city=  villeRepository.findById(id);	
	if (! city.isPresent()) 
		throw new CityNotFoundException("id: "+ id);
		
		return city.get();
	}
@PostMapping("/Cities/Save")
public ResponseEntity<Object> addCity(@RequestBody Ville city) {
	Ville addCity = villeRepository.save(city);
URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addCity.getID_Ville()).toUri();
	return ResponseEntity.created(location).build();
}

@DeleteMapping("/Cities/{id}")
public void  deletecity(@PathVariable int id) {
	villeRepository.deleteById(id);;	
	
}
}
