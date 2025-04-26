package com.capgemini.vehiculeservice.controllers;


import com.capgemini.vehiculeservice.dtos.ErrorDto;
import com.capgemini.vehiculeservice.dtos.VehiculeDto;
import com.capgemini.vehiculeservice.exceptions.InvalidRequestException;
import com.capgemini.vehiculeservice.populators.VehiculePopulator;
import com.capgemini.vehiculeservice.services.VehiculeService;
import com.capgemini.vehiculeservice.validators.VehiculeValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class VehiculeController {

    private final VehiculeService vehiculeService;
    private final VehiculePopulator vehiculePopulator;

    public VehiculeController(VehiculeService vehiculeService, VehiculePopulator vehiculePopulator) {
        this.vehiculeService = vehiculeService;
        this.vehiculePopulator = vehiculePopulator;
    }

    @GetMapping("/")
    @Operation(summary = "Show all Vehicules")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Show all Vehicules", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<List<VehiculeDto>> showAll() {
        return ResponseEntity.ok().body(vehiculeService.findAll());
    }

    @GetMapping("/list")
    @Operation(summary = "Show all vehicules with pagination")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Show all vehicules with pagination", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<Page<VehiculeDto>> showAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok().body(vehiculeService.findAll(PageRequest.of(page, size, Sort.by(sortBy))));
    }

    @PostMapping("/create")
    @Operation(summary = "Creation Vehicule")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Creation Vehicule", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<VehiculeDto> create(@RequestBody VehiculeDto garageDto) throws InvalidRequestException {
        List<String> errors = VehiculeValidator.validate(garageDto);
        if (!errors.isEmpty()) {
            log.error("Vehicule dto is not valid {}", garageDto);
            throw new InvalidRequestException("Vehicule dto is not valid request " + errors);
        }
        return ResponseEntity.ok().body(vehiculeService.createVehicule(garageDto));
    }

    @PostMapping("/edit")
    @Operation(summary = "Edite Garage")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Edition vehicule", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<VehiculeDto> edit(@RequestBody VehiculeDto vehiculeDto) throws InvalidRequestException {
        List<String> errors = VehiculeValidator.validate(vehiculeDto);
        if (!errors.isEmpty()) {
            log.error("Vehicule dto is not valid {}", vehiculeDto);
            throw new InvalidRequestException("Garege dto is not valid request " + errors);
        }
        return ResponseEntity.ok().body(vehiculeService.save(vehiculeDto));
    }

    @GetMapping("/vehicule/{id}")
    @Operation(summary = "Find vehicule by Id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Find vehicule by Id ", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<VehiculeDto> findById(@PathVariable(required = true) Long id)  {
        return ResponseEntity.ok().body(vehiculeService.findById(id));
    }

    @GetMapping("/remove")
    @Operation(summary = "remove vehicule by Id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "remove vehicule by Id ", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<String> removeById(@RequestParam(required = true) Long id) {
        vehiculeService.removeById(id);
        return new ResponseEntity<>("Vehicule " + id +" removed", HttpStatus.OK);
    }
}
