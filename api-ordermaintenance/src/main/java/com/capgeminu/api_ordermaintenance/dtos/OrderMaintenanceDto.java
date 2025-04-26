package com.capgeminu.api_ordermaintenance.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMaintenanceDto {
    private Long orderId;
    private GarageDto garage;
    private VehiculeDto vehicule;
    private Instant creationDate;
    private Instant lastModifiedDate;
}
