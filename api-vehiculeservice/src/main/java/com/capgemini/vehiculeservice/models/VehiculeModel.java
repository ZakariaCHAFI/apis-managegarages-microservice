package com.capgemini.vehiculeservice.models;

import com.capgemini.vehiculeservice.enums.EngineEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "vehicule")
public class VehiculeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String yearManufact;
    @Enumerated(EnumType.STRING)
    private EngineEnum engine;
}
