package NIRS.repository;

import NIRS.entity.PurchaseHistories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseHistoriesRepository extends JpaRepository<PurchaseHistories,Long> {
//    List<PurchaseHistories> findByCar_CarId(Long carId);
}
