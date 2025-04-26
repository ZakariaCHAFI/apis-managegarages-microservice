package com.capgemini.garageservice.validators;

import com.capgemini.garageservice.dtos.GarageDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class GarageValidator {

    public static final String PATTERB_REGEX_MAIL = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    public static List<String> validate(GarageDto garageDto) {
        List<String> errors = new ArrayList<>();
        if (garageDto == null) {
            errors.add("request body cannot be null");
            return errors;
        }
        if (StringUtils.isBlank(garageDto.getName())) {
            errors.add("name param cannot be null");
        }
        if (StringUtils.isBlank(garageDto.getEmail())) {
            errors.add("email param cannot be null");
        } else if (!Pattern.compile(PATTERB_REGEX_MAIL).matcher(garageDto.getEmail()).matches()) {
            errors.add("email param not valid, exemple username@domaine.com");
        }
        if (StringUtils.isBlank(garageDto.getAddress())) {
            errors.add("address param cannot be null");
        }
        if (StringUtils.isBlank(garageDto.getTelephone())) {
            errors.add("telephone param cannot be null");
        }
        return errors;
    }
}
