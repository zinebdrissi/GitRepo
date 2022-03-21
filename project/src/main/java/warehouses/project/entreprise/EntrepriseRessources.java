package warehouses.project.entreprise;

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

import warehouses.project.user.UserNotFoundException;

@RestController
public class EntrepriseRessources {
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	

		@GetMapping("/Entreprise")
		public List<Entreprise> retrieveAllEntreprise(){
			return entrepriseRepository.findAll();
			}
		
		
		
		@GetMapping("/findEntreprise/{id}")
		public Entreprise retrieveEntreprise(@PathVariable int id) {
				Optional<Entreprise> company=  entrepriseRepository.findById(id);	
			if (!company.isPresent()) 
				throw new UserNotFoundException("id: "+ id);
				
				return entrepriseRepository.getOne(id);
			}
			
		
		@PostMapping("/SaveEntreprise")
	
		public ResponseEntity<Object> creatEntreprise(@RequestBody Entreprise entreprise) {
			Entreprise saveEntreprise = entrepriseRepository.save(entreprise);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveEntreprise.getID_entreprise()).toUri();
			return ResponseEntity.created(location).build();
		}
		
		@DeleteMapping("/Entreprise/{id}")
		public void  deleteEntreprise(@PathVariable int id) {
			entrepriseRepository.deleteById(id);;	
		}
	
	
	
	
	
	

}
