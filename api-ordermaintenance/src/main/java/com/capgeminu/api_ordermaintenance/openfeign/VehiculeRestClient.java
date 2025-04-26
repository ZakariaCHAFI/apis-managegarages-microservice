package com.capgeminu.api_ordermaintenance.openfeign;

import com.capgeminu.api_ordermaintenance.dtos.GarageDto;
import com.capgeminu.api_ordermaintenance.dtos.VehiculeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehiculeservice", url = "http://localhost:8082")
public interface VehiculeRestClient {

    @GetMapping("/api/vehicule/{id}")
    VehiculeDto getVehiculeById(@PathVariable Long id);

    @GetMapping("/api/list")
    PagedModel<VehiculeDto> getAllVehicule();
}
