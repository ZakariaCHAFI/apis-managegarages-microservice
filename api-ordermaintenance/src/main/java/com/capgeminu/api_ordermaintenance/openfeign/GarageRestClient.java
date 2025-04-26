package com.capgeminu.api_ordermaintenance.openfeign;

import com.capgeminu.api_ordermaintenance.dtos.GarageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "garageservice", url = "http://localhost:8081")
public interface GarageRestClient {

    @GetMapping("/api/garage/{id}")
    GarageDto getGarageById(@PathVariable Long id);

    @GetMapping("/api/list")
    PagedModel<GarageDto> getAllGarages();
}
