package NIRS.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ServiceDTO {
    private Long catalog_id;
    private LocalDate date;
}
