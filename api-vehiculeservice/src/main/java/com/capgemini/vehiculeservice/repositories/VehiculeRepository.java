package com.capgemini.vehiculeservice.repositories;

import com.capgemini.vehiculeservice.models.VehiculeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends JpaRepository<VehiculeModel, Long> {
}
