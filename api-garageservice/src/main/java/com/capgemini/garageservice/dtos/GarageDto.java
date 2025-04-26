package com.capgemini.garageservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GarageDto {

    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String email;
}
