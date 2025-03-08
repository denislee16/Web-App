package NIRS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase_histories")
public class PurchaseHistories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchase_id;
    private Date date;
    private int totalCost;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients clients;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Workers workers;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Cars cars;
    //private List<Cars> cars = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    //private List<Service> service = new ArrayList<>();
}
