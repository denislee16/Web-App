package NIRS.repository;

import NIRS.entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {
//    List<Cars> findByClient_ClientId(Long clientId);
}
