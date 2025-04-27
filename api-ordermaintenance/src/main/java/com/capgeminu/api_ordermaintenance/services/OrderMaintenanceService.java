package com.capgeminu.api_ordermaintenance.services;

import com.capgeminu.api_ordermaintenance.dtos.GarageDto;
import com.capgeminu.api_ordermaintenance.dtos.OrderMaintenanceDto;
import com.capgeminu.api_ordermaintenance.dtos.VehiculeDto;
import com.capgeminu.api_ordermaintenance.models.OrderMaintenanceModel;
import com.capgeminu.api_ordermaintenance.openfeign.GarageRestClient;
import com.capgeminu.api_ordermaintenance.openfeign.VehiculeRestClient;
import com.capgeminu.api_ordermaintenance.repositories.OrderMaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMaintenanceService {

    private final OrderMaintenanceRepository orderMaintenanceRepository;
    private final VehiculeRestClient vehiculeRestClient;
    private final GarageRestClient garageRestClient;

    public OrderMaintenanceService(OrderMaintenanceRepository orderMaintenanceRepository, VehiculeRestClient vehiculeRestClient, GarageRestClient garageRestClient) {
        this.orderMaintenanceRepository = orderMaintenanceRepository;
        this.vehiculeRestClient = vehiculeRestClient;
        this.garageRestClient = garageRestClient;
    }

    public OrderMaintenanceDto createOrder(GarageDto garageDto, VehiculeDto vehiculeDto) {
        OrderMaintenanceModel orderMaintenanceModel = new OrderMaintenanceModel();
        orderMaintenanceModel.setGarageId(garageDto.getId());
        orderMaintenanceModel.setVehiculeId(vehiculeDto.getId());
        orderMaintenanceModel = orderMaintenanceRepository.save(orderMaintenanceModel);

        return getOrderMaintenanceDto(garageDto, vehiculeDto, orderMaintenanceModel);
    }

    public List<OrderMaintenanceDto> findAll() {
        return orderMaintenanceRepository.findAll().stream().map(order -> OrderMaintenanceDto.builder().
                orderId(order.getId()).
                garage(garageRestClient.getGarageById(order.getGarageId()))
                .vehicule(vehiculeRestClient.getVehiculeById( order.getVehiculeId()))
                .creationDate(order.getCreationDate())
                .lastModifiedDate(order.getLastModifiedDate()).build()
        ).collect(Collectors.toList());
    }

    public List<OrderMaintenanceDto> findOrderMaintenanceByGarageId(GarageDto garageDto) {
        List<OrderMaintenanceModel> orderMaintenanceModels = orderMaintenanceRepository.findOrderMaintenanceByGarageId(garageDto.getId());
        List<OrderMaintenanceDto> orderMaintenanceDtos = orderMaintenanceModels.stream().map(order -> OrderMaintenanceDto.builder().
                orderId(order.getId()).
                garage(garageDto).vehicule(vehiculeRestClient.getVehiculeById(order.getVehiculeId()))
                .creationDate(order.getCreationDate())
                .lastModifiedDate(order.getLastModifiedDate()).build()
                ).collect(Collectors.toList());
        return orderMaintenanceDtos;
    }

    public List<OrderMaintenanceDto> findOrderMaintenanceByBrand(List<VehiculeDto> vehiculeDtos) {
        List<OrderMaintenanceModel> orderMaintenanceModels = vehiculeDtos.stream().map(v -> orderMaintenanceRepository.findById(v.getId()).get()).collect(Collectors.toList()) ;
        return orderMaintenanceModels.stream().map(order -> OrderMaintenanceDto.builder().
                orderId(order.getId()).
                garage(garageRestClient.getGarageById(order.getGarageId()))
                .vehicule(vehiculeRestClient.getVehiculeById( order.getVehiculeId()))
                .creationDate(order.getCreationDate())
                .lastModifiedDate(order.getLastModifiedDate()).build()
        ).collect(Collectors.toList());
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
