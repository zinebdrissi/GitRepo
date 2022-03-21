package warehouses.project.ville;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public class CityNotFoundException extends RuntimeException {

		public CityNotFoundException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}


	}

