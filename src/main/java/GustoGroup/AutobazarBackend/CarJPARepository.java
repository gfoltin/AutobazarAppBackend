package GustoGroup.AutobazarBackend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarJPARepository extends JpaRepository<Car, Long> {
	List<Car> findByUsername(String username);
	List<Car> findAllByNameContainingIgnoreCase(String filter);
	List<Car> findAllByColorContainingIgnoreCase(String filter);
	List<Car> findAllByEngineContainingIgnoreCase(String filter);
	List<Car> findAllByAvailableContainingIgnoreCase(String filter);
}
