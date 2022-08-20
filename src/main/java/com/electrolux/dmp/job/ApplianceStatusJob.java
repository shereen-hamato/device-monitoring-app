package com.electrolux.dmp.job;

import com.electrolux.dmp.controller.ApplianceStatusController;
import com.electrolux.dmp.repository.ApplianceStatusRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Component
@Transactional
public class ApplianceStatusJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplianceStatusController.class);

    @Autowired
    ApplianceStatusRepo applianceStatusRepo;


    @Scheduled(fixedRate = 60000)
    public void setApplianceStatusDown() {
        Integer updatedRecords = applianceStatusRepo.updateByLastModified(new Timestamp(System.currentTimeMillis() - 120000));
        LOGGER.info("{} appliances status updated to down", updatedRecords);

    }
}
