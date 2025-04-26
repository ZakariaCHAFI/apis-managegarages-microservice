package com.capgemini.garageservice.controllers;

import com.capgemini.garageservice.dtos.ErrorDto;
import com.capgemini.garageservice.dtos.GarageDto;
import com.capgemini.garageservice.exceptions.InvalidRequestException;
import com.capgemini.garageservice.services.GarageService;
import com.capgemini.garageservice.validators.GarageValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class GarageController {

    private final GarageService garageService;
    private final GarageValidator garageValidator;

    public GarageController(GarageService garageService, GarageValidator garageValidator) {
        this.garageService = garageService;
        this.garageValidator = garageValidator;
    }

    @GetMapping("/")
    @Operation(summary = "Show all garages")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Show all garage", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<List<GarageDto>> showAll() {
        return ResponseEntity.ok().body(garageService.findAll());
    }

    @GetMapping("/list")
    @Operation(summary = "Show all garages with pagination")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Show all garages with pagination", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<Page<GarageDto>> showAllCars(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok().body(garageService.findAll(PageRequest.of(page, size, Sort.by(sortBy))));
    }

    @PostMapping("/create")
    @Operation(summary = "Creation Garage")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Creation Garage", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<GarageDto> create(@RequestBody GarageDto garageDto) throws InvalidRequestException {
        List<String> errors = GarageValidator.validate(garageDto);
        if (!errors.isEmpty()) {
            log.error("Garage dto is not valid {}", garageDto);
            throw new InvalidRequestException("Garege dto is not valid request " + errors);
        }
        return ResponseEntity.ok().body(garageService.createGarage(garageDto));
    }

    @PostMapping("/edit")
    @Operation(summary = "save Garage")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Save Garage", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<GarageDto> edit(@RequestBody GarageDto garageDto) throws InvalidRequestException {
        List<String> errors = GarageValidator.validate(garageDto);
        if (!errors.isEmpty()) {
            log.error("Garage dto is not valid {}", garageDto);
            throw new InvalidRequestException("Garege dto is not valid request " + errors);
        }
        return ResponseEntity.ok().body(garageService.saveGarage(garageDto));
    }

    @GetMapping("/garage/{id}")
    @Operation(summary = "Find Garage by Id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Find Garage by Id ", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
    public ResponseEntity<GarageDto> findGarageById(@PathVariable(required = true) Long id) throws InvalidRequestException {
        return ResponseEntity.ok().body(garageService.findById(id));
    }
}
