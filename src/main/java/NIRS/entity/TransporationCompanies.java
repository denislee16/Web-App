package NIRS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transporation_companies")
public class TransporationCompanies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transporation_id;
    private String name;
    private String address;
    private String number;
}
