package com.capgemini.vehiculeservice.validators;

import com.capgemini.vehiculeservice.dtos.VehiculeDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehiculeValidator {


    public static List<String> validate(VehiculeDto vehiculeDto) {
        List<String> errors = new ArrayList<>();
        if (vehiculeDto == null) {
            errors.add("request body cannot be null");
            return errors;
        }
        if (StringUtils.isBlank(vehiculeDto.getBrand())) {
            errors.add("brand param cannot be null");
        }

        if (StringUtils.isBlank(vehiculeDto.getYearManufact())) {
            errors.add("year param cannot be null");
        }
        if (StringUtils.isBlank(vehiculeDto.getEngine().name())) {
            errors.add("engine param cannot be null");
        }
        return errors;
    }
}
