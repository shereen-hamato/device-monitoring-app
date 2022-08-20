package com.electrolux.dmp.service;

import com.electrolux.dmp.costants.Status;
import com.electrolux.dmp.dto.ApplianceStatusDto;
import com.electrolux.dmp.model.Appliance;
import com.electrolux.dmp.model.ApplianceStatus;
import com.electrolux.dmp.model.Customer;
import com.electrolux.dmp.repository.ApplianceRepo;
import com.electrolux.dmp.repository.ApplianceStatusRepo;
import com.electrolux.dmp.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApplianceStatusService {

    @Autowired
    ApplianceStatusRepo applianceStatusRepo;

    @Autowired
    ApplianceRepo applianceRepo;

    @Autowired
    CustomerRepo customerRepo;

    public List<ApplianceStatus> getApplianceStatusList() {

        return applianceStatusRepo.findAll();
    }


    public ApplianceStatus getApplianceStatusById(Integer id) {
        return applianceStatusRepo.findById(id).orElse(null);
    }

    public List<ApplianceStatus> getApplianceStatusByCustomerId(String customerId) {
        Customer customer = customerRepo.findByCustomerId(customerId).orElseThrow(() -> new NoSuchElementException());
        return applianceStatusRepo.findByCustomer(customer);
    }

    public ApplianceStatus createApplianceStatus(ApplianceStatusDto applianceStatusDto) {
        Customer customer = customerRepo.findByCustomerId(applianceStatusDto.getCustomerId()).orElseThrow(() -> new NoSuchElementException());
        Appliance appliance = applianceRepo.findByApplianceId(applianceStatusDto.getApplianceId()).orElseThrow(() -> new NoSuchElementException());
        return applianceStatusRepo.save(new ApplianceStatus(appliance, customer, Status.up.name(), new Timestamp(new Date().getTime())));
    }

    public ApplianceStatus updateStatusForAppliance(Integer id, Status status) {
        ApplianceStatus applianceStatus = applianceStatusRepo.findById(id).orElseThrow(() -> new NoSuchElementException());
        applianceStatus.setStatus(status.name());
        applianceStatus.setLast_modified(new Timestamp(new Date().getTime()));
        return applianceStatusRepo.save(applianceStatus);
    }


}
