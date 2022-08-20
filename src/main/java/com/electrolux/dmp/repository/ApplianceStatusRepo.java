package com.electrolux.dmp.repository;

import com.electrolux.dmp.model.ApplianceStatus;
import com.electrolux.dmp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplianceStatusRepo extends JpaRepository<ApplianceStatus, Integer> {

    @Modifying
    @Query(value = "update appliance_status set status = 'down' , last_modified = CURRENT_TIME WHERE last_modified < (:time) and status = 'up'", nativeQuery = true)
    Integer updateByLastModified(@Param("time") Timestamp time);

    List<ApplianceStatus> findByCustomer(Customer customer);
}
