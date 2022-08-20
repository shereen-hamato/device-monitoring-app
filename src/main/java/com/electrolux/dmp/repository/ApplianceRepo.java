package com.electrolux.dmp.repository;

import com.electrolux.dmp.model.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplianceRepo extends JpaRepository<Appliance, Integer> {
    Optional<Appliance> findByApplianceId(String appliance_id);
}
