package com.capgemini.vehiculeservice.services;

import com.capgemini.vehiculeservice.dtos.VehiculeDto;
import com.capgemini.vehiculeservice.exceptions.EntityNotFoundException;
import com.capgemini.vehiculeservice.models.VehiculeModel;
import com.capgemini.vehiculeservice.populators.VehiculePopulator;
import com.capgemini.vehiculeservice.repositories.VehiculeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculePopulator vehiculePopulator;

    public VehiculeService(VehiculeRepository vehiculeRepository, VehiculePopulator vehiculePopulator) {
        this.vehiculeRepository = vehiculeRepository;
        this.vehiculePopulator = vehiculePopulator;
    }


    public List<VehiculeDto> findAll() {
        List<VehiculeModel> garageModels = vehiculeRepository.findAll();
        return garageModels.stream().map(vehiculePopulator::toDto).collect(Collectors.toList());
    }

    public Page<VehiculeDto> findAll(Pageable pageable) {
        return vehiculeRepository.findAll(pageable).map(vehiculePopulator::toDto);
    }

    /**
     * spec : Récupération d’un garage spécifique (par ID).
     *
     * @param id
     */
    public VehiculeDto findById(Long id) {
        Assert.notNull(id, "Id vehicule, mustn't be null");
        VehiculeModel vehiculeModel = vehiculeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vehicule with id  = " + id + " not found."));
        return vehiculePopulator.toDto(vehiculeModel);
    }

    /**
     * spec : Création de garages.
     *
     * @param vehiculeDto
     */
    public VehiculeDto createVehicule(VehiculeDto vehiculeDto) {
        VehiculeModel vehiculeModel = vehiculeRepository.save(vehiculePopulator.toModel(vehiculeDto));
        return vehiculePopulator.toDto(vehiculeModel);
    }

    /**
     * spec : modification  de garages.
     *
     * @param vehiculeDto
     */
    public VehiculeDto save(VehiculeDto vehiculeDto) {
        Assert.notNull(vehiculeDto.getId(), "Id vehicule, mustn't be null");
        vehiculeRepository.findById(vehiculeDto.getId()).orElseThrow(() -> new EntityNotFoundException("Vehicule with id  = " + vehiculeDto.getId() + " not found."));
        VehiculeModel vehiculeModel = vehiculeRepository.save(vehiculePopulator.toModel(vehiculeDto));
        return vehiculePopulator.toDto(vehiculeModel);
    }

    /**
     * spec : suppression de garages par id.
     *
     * @param id
     */
    public void removeById(Long id) {
        vehiculeRepository.deleteById(id);
    }

}
