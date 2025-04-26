package com.capgeminu.api_ordermaintenance.services;

import com.capgeminu.api_ordermaintenance.dtos.GarageDto;
import com.capgeminu.api_ordermaintenance.dtos.OrderMaintenanceDto;
import com.capgeminu.api_ordermaintenance.dtos.VehiculeDto;
import com.capgeminu.api_ordermaintenance.models.OrderMaintenanceModel;
import com.capgeminu.api_ordermaintenance.repositories.OrderMaintenanceRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderMaintenanceService {

    private final OrderMaintenanceRepository orderMaintenanceRepository;

    public OrderMaintenanceService(OrderMaintenanceRepository orderMaintenanceRepository) {
        this.orderMaintenanceRepository = orderMaintenanceRepository;
    }

    public OrderMaintenanceDto createOrder(GarageDto garageDto, VehiculeDto vehiculeDto) {
        OrderMaintenanceModel orderMaintenanceModel = new OrderMaintenanceModel();
        orderMaintenanceModel.setGarageId(garageDto.getId());
        orderMaintenanceModel.setVehiculeId(vehiculeDto.getId());
        orderMaintenanceModel = orderMaintenanceRepository.save(orderMaintenanceModel);

        return getOrderMaintenanceDto(garageDto, vehiculeDto, orderMaintenanceModel);
    }

    private static OrderMaintenanceDto getOrderMaintenanceDto(GarageDto garageDto, VehiculeDto vehiculeDto, OrderMaintenanceModel orderMaintenanceModel) {
        OrderMaintenanceDto orderMaintenanceDto = new OrderMaintenanceDto();
        orderMaintenanceDto.setOrderId(orderMaintenanceModel.getId());
        orderMaintenanceDto.setGarage(garageDto);
        orderMaintenanceDto.setVehicule(vehiculeDto);
        orderMaintenanceDto.setCreationDate(orderMaintenanceModel.getCreationDate());
        orderMaintenanceDto.setLastModifiedDate(orderMaintenanceModel.getLastModifiedDate());
        return orderMaintenanceDto;
    }
}
