package NIRS.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PurchaseHistoriesDTO {
    private Long client_id;
    private Long worker_id;
    private Long car_id;
    private Long service_id;
    private Date date;
    private int totalCost;
}
