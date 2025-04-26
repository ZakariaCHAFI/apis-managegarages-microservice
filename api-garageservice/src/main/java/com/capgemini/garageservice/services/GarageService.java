package com.capgemini.garageservice.services;

import com.capgemini.garageservice.dtos.GarageDto;
import com.capgemini.garageservice.exceptions.EntityNotFoundException;
import com.capgemini.garageservice.models.GarageModel;
import com.capgemini.garageservice.populators.GaragePopulator;
import com.capgemini.garageservice.repositories.GarageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GarageService {

    private final GarageRepository garageRepository;
    private final GaragePopulator garagePopulator;

    public GarageService(GarageRepository garageRepository, GaragePopulator garagePopulator) {
        this.garageRepository = garageRepository;
        this.garagePopulator = garagePopulator;
    }


    public List<GarageDto> findAll() {
        List<GarageModel> garageModels = garageRepository.findAll();
        return garageModels.stream().map(garagePopulator::toDto).collect(Collectors.toList());
    }

    public Page<GarageDto> findAll(Pageable pageable) {
        return garageRepository.findAll(pageable).map(garagePopulator::toDto);
    }

    /**
     * spec : Récupération d’un garage spécifique (par ID).
     *
     * @param id
     */
    public GarageDto findById(Long id) {
        Assert.notNull(id, "Id garage, mustn't be null");
        GarageModel garageModel = garageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Garage with id  = " + id + " not found."));
        return garagePopulator.toDto(garageModel);
    }

    /**
     * spec : Création de garages.
     *
     * @param garageDto
     */
    public GarageDto createGarage(GarageDto garageDto) {
        GarageModel garageModel = garageRepository.save(garagePopulator.toModel(garageDto));
        return garagePopulator.toDto(garageModel);
    }

    /**
     * spec : modification  de garages.
     *
     * @param garageDto
     */
    public GarageDto saveGarage(GarageDto garageDto) {
        Assert.notNull(garageDto.getId(), "Id garage, mustn't be null");
        GarageModel garageModel = garageRepository.findById(garageDto.getId()).orElseThrow(() -> new EntityNotFoundException("Garage with id  = " + garageDto.getId() + " not found."));
        garageModel = garageRepository.save(garagePopulator.toModel(garageDto));
        return garagePopulator.toDto(garageModel);
    }

    /**
     * spec : suppression de garages par id.
     *
     * @param id
     */
    public void removeGarage(Long id) {
        garageRepository.deleteById(id);
    }

}
