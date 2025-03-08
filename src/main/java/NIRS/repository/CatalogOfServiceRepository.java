package NIRS.repository;

import NIRS.entity.CatalogOfService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogOfServiceRepository extends JpaRepository<CatalogOfService,Long> {
}
