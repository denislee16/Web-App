package NIRS.dto;

import lombok.Data;

@Data
public class CarsDTO {
    private String model;
    private int cost;
    private int makeYear;
    private int mileage;
    private String color;
    private Long supplier_id;
    private Long transporation_id;
}
