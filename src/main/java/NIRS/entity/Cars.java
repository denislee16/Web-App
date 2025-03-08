package NIRS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.catalog.Catalog;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long car_id;
    private String model;
    private int cost;
    private int makeYear;
    private int mileage;
    private String color;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Suppliers suppliers;
    @ManyToOne
    @JoinColumn(name = "transporation_id")
    private TransporationCompanies transporationCompanies;
}
