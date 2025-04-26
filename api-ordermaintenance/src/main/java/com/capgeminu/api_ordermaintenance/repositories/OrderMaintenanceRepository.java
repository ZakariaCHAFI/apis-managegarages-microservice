package com.capgeminu.api_ordermaintenance.repositories;

import com.capgeminu.api_ordermaintenance.models.OrderMaintenanceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMaintenanceRepository extends JpaRepository<OrderMaintenanceModel, Long> {
}
