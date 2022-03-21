package warehouses.project.entrepot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EntrepotDaoServices {


		private static List<Entrepot> entrepot = new ArrayList<>();
		static {
			
			}
		
		//show all warehouse
		public List<Entrepot> find() {
			return entrepot;
			
		}
		
		//show one warehouse
		public Entrepot findOne(int id){
			for (Entrepot depot:entrepot) {
				if (depot.getId()==id)
					return depot;
					}
			
		return null;
		 }
		
		//add a warehouse
		public Entrepot AddWarehouses(Entrepot entrepots) {
			entrepot.add(entrepots);
			return entrepots;
		}
		
				
	//delete a warehouse
		public Entrepot DeleteWarehouses(int id) {
			Iterator<Entrepot> iterator= entrepot.iterator();
			while(iterator.hasNext()) {
				Entrepot entrepot= iterator.next();
				  if (entrepot.getId()== id) {
					  iterator.remove();
					  return entrepot;
				  }
			  }
			  return null;
			  }
}
