package com.capgeminu.api_ordermaintenance.openfeign;

import com.capgeminu.api_ordermaintenance.dtos.VehiculeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "vehiculeservice", url = "http://localhost:8082")
public interface VehiculeRestClient {

    @GetMapping("/api/vehicule/{id}")
    VehiculeDto getVehiculeById(@PathVariable Long id);

    @GetMapping("/api/vehicule")
    List<VehiculeDto> getVehiculeByBrand(@RequestParam String brand);

    @GetMapping("/api/list")
    PagedModel<VehiculeDto> getAllVehicule();
}
