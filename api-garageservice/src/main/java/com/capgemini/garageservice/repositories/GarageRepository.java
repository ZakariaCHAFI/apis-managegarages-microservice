package com.capgemini.garageservice.repositories;

import com.capgemini.garageservice.models.GarageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarageRepository extends JpaRepository<GarageModel, Long> {
}
