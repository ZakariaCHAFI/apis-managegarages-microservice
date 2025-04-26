package com.capgeminu.api_ordermaintenance.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculeDto {

    private Long id;
    private String brand;
    private String yearManufact;
    //private EngineEnum engine;
}
