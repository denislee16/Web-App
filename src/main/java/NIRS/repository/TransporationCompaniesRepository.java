package NIRS.repository;

import NIRS.entity.TransporationCompanies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransporationCompaniesRepository extends JpaRepository<TransporationCompanies,Long> {
}
