package com.capgemini.vehiculeservice.populators;

import com.capgemini.vehiculeservice.dtos.VehiculeDto;
import com.capgemini.vehiculeservice.models.VehiculeModel;
import org.springframework.stereotype.Component;

@Component
public class VehiculePopulator {

    public VehiculeModel toModel(VehiculeDto vehiculeDto) {
        if (vehiculeDto == null) {
            return null;
        }

        VehiculeModel.VehiculeModelBuilder vehiculeModel = VehiculeModel.builder();
        vehiculeModel.id(vehiculeDto.getId());
        vehiculeModel.brand(vehiculeDto.getBrand());
        vehiculeModel.yearManufact(vehiculeDto.getYearManufact());
        vehiculeModel.engine(vehiculeDto.getEngine());
        return vehiculeModel.build();
    }

    public VehiculeDto toDto(VehiculeModel vehiculeModel) {
        if (vehiculeModel == null) {
            return null;
        }

        VehiculeDto.VehiculeDtoBuilder vehiculeDto = VehiculeDto.builder();
        vehiculeDto.id(vehiculeModel.getId());
        vehiculeDto.brand(vehiculeModel.getBrand());
        vehiculeDto.yearManufact(vehiculeModel.getYearManufact());
        vehiculeDto.engine(vehiculeModel.getEngine());
        return vehiculeDto.build();
    }
}
