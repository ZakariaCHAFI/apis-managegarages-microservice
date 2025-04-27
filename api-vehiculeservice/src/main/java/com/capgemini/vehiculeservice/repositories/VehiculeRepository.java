package com.capgemini.vehiculeservice.repositories;

import com.capgemini.vehiculeservice.models.VehiculeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<VehiculeModel, Long> {

    List<VehiculeModel> findByBrand(String brand);
}
