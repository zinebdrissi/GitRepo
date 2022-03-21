package warehouses.project.entrepot;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import warehouses.project.user.UserNotFoundException;
import warehouses.project.user.Users;

@Controller
public class EntrepotRessource {

	
@Autowired	
private EntrepotRepository entrepotRepository;
	
	//retrieveAllwarehouse
	@GetMapping("/Warehouse")
	public String viewWarehousePage(Model model){
	 List<Entrepot> Entrepotlist = entrepotRepository.findAll();
         model.addAttribute("Entrepotlist", Entrepotlist);
         System.out.print("Get /Warehouse ");
         return "Warehouse";
		}
	
	
	
	
	@GetMapping("/findWarehouse/{id}")
	public String retrieveWarehouses(@PathVariable  int id, Model model) {
			Optional<Entrepot> entrepot=  entrepotRepository.findById(id);	
		if (!entrepot.isPresent()) 
			throw new UserNotFoundException("id: "+ id);	
		
		 model.addAttribute("entrepot",entrepot.get());
			
	         return "WarehouseById";
		}
		
	
	
	@PostMapping("/SaveWarhouse")
	
		public ResponseEntity<Object> creatWarehouses(@RequestBody Entrepot entrepot) {
		Entrepot saveEntrepot = entrepotRepository.save(entrepot);
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveEntrepot.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
		
		
		@GetMapping("/Warehouse/{id}")
		public String  deleteWarehouses(@PathVariable int id) {		
			entrepotRepository.deleteById(id);
			return "redirect:/Home";
	}
}     
