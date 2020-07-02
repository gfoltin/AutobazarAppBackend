package GustoGroup.AutobazarBackend;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CarResource {
	
	@Autowired
	private CarJPARepository repository;
	
	@GetMapping("/cars")
	public List<Car> getAllCars(){
		return repository.findAll();
	}
	
	@GetMapping("users/{username}/cars")
	public List<Car> getAllCarsByUsername(@PathVariable String username){
		return repository.findByUsername(username);
	}
	
	@GetMapping("/cars/{filter}/{option}")
	public List<Car> filterData(@PathVariable String filter, @PathVariable String option){
		if(option.equals("name")) return repository.findAllByNameContainingIgnoreCase(filter);
		else if (option.equals("color")) return repository.findAllByColorContainingIgnoreCase(filter);
		else if (option.equals("engine")) return repository.findAllByEngineContainingIgnoreCase(filter);
		else if (option.equals("available")) return repository.findAllByAvailableContainingIgnoreCase(filter);
		else return null;
	}
	
	@GetMapping("users/{username}/cars/{id}")
	public Car getCar(@PathVariable String username, @PathVariable Long id) {
		return repository.findById(id).get();
	}
	
	@DeleteMapping("users/{username}/cars/{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable String username, @PathVariable long id){
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("users/{username}/cars/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable String username, @PathVariable long id, @RequestBody Car car){
		Car updatedCar = repository.save(car);
		return new ResponseEntity<Car>(updatedCar, HttpStatus.OK);
	}
	
	@PostMapping("users/{username}/cars")
	public ResponseEntity<Car> createCar(@PathVariable String username, @RequestBody Car car){
		Car createdCar = repository.save(car);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCar.getId()).toUri();
				
		return ResponseEntity.created(uri).build();
	}
}