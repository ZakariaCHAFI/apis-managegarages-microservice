package com.capgeminu.api_ordermaintenance.controllers;

import com.capgeminu.api_ordermaintenance.dtos.ErrorDto;
import com.capgeminu.api_ordermaintenance.dtos.GarageDto;
import com.capgeminu.api_ordermaintenance.dtos.OrderMaintenanceDto;
import com.capgeminu.api_ordermaintenance.dtos.VehiculeDto;
import com.capgeminu.api_ordermaintenance.exceptions.EntityNotFoundException;
import com.capgeminu.api_ordermaintenance.openfeign.GarageRestClient;
import com.capgeminu.api_ordermaintenance.openfeign.VehiculeRestClient;
import com.capgeminu.api_ordermaintenance.services.OrderMaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class OrderMaintenanceController {

    @Autowired
    private GarageRestClient garageRestClient;
    @Autowired
    private VehiculeRestClient vehiculeRestClient;
    @Autowired
    OrderMaintenanceService orderMaintenanceService;

    @PostMapping("/placeOrder")
    @Operation(summary = "Creation order maintenance")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Creation order maintenance", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<OrderMaintenanceDto> create(@RequestParam(required = true) Long garageId, @RequestParam(required = true) Long vehiculeId ) {
        GarageDto garageDto = garageRestClient.getGarageById(garageId);
        VehiculeDto vehiculeDto = vehiculeRestClient.getVehiculeById(vehiculeId);
        if(garageDto == null) {
            log.error("Garage dto not found, {0}", garageDto);
            throw new EntityNotFoundException("Garege not found, is not valid request ");
        }
        if(vehiculeDto == null) {
            log.error("Vehicule dto not found, {0}", vehiculeDto);
            throw new EntityNotFoundException("Vehicule not found, is not valid request ");
        }
        return ResponseEntity.ok().body(orderMaintenanceService.createOrder(garageDto, vehiculeDto));
    }

    @GetMapping("/listVehicules")
    @Operation(summary = "Lister les véhicules d’un garage spécifique.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Lister les véhicules d’un garage spécifique.", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<List<OrderMaintenanceDto>> listVehiculeByGarageId(@RequestParam(required = true) Long garageId ) {
        GarageDto garageDto = garageRestClient.getGarageById(garageId);
        if(garageDto == null) {
            log.error("Garage dto not found, {0}", garageDto);
            throw new EntityNotFoundException("Garege not found, is not valid request ");
        }
        return ResponseEntity.ok().body(orderMaintenanceService.findOrderMaintenanceByGarageId(garageDto));
    }
}
