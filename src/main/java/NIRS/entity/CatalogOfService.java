package NIRS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.catalog.Catalog;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "catalog_of_service")
public class CatalogOfService {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long catalog_id;
    private String name;
    private int cost;
}
