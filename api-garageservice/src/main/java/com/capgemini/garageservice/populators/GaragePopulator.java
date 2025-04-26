package com.capgemini.garageservice.populators;

import com.capgemini.garageservice.dtos.GarageDto;
import com.capgemini.garageservice.models.GarageModel;
import org.springframework.stereotype.Component;

@Component
public class GaragePopulator {

    public GarageModel toModel(GarageDto garageDto) {
        if (garageDto == null) {
            return null;
        }

        GarageModel.GarageModelBuilder garageModel = GarageModel.builder();
        garageModel.id(garageDto.getId());
        garageModel.name(garageDto.getName());
        garageModel.email(garageDto.getEmail());
        garageModel.telephone(garageDto.getTelephone());
        garageModel.address(garageDto.getAddress());
        return garageModel.build();
    }

    public GarageDto toDto(GarageModel garageModel) {
        if (garageModel == null) {
            return null;
        }

        GarageDto.GarageDtoBuilder carageDto = GarageDto.builder();
        carageDto.id(garageModel.getId());
        carageDto.name(garageModel.getName());
        carageDto.email(garageModel.getEmail());
        carageDto.telephone(garageModel.getTelephone());
        carageDto.address(garageModel.getAddress());
        return carageDto.build();
    }
}
