package com.electrolux.dmp.controller;

import com.electrolux.dmp.constant.Status;
import com.electrolux.dmp.dto.ApplianceStatusDto;
import com.electrolux.dmp.mapper.ApplianceStatusMapper;
import com.electrolux.dmp.model.ApplianceStatus;
import com.electrolux.dmp.service.ApplianceStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/appliance-status")
public class ApplianceStatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplianceStatusController.class);

    @Autowired
    private ApplianceStatusService applianceStatusService;

    @Autowired
    private ApplianceStatusMapper applianceStatusMapper;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ApplianceStatusDto> getAppliancesStatus() {
        List<ApplianceStatus> applianceStatusList = applianceStatusService.getApplianceStatusList();
        List<ApplianceStatusDto> applianceStatusDtos=  applianceStatusList.stream()
                .map(applianceStatusMapper::convertToDto)
                .collect(Collectors.toList());

        return applianceStatusDtos;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApplianceStatusDto getApplianceStatusById(@PathVariable("id") Integer id) {
        LOGGER.info("Get /appliance status {}", id);
        return applianceStatusMapper.convertToDto(applianceStatusService.getApplianceStatusById(id));
    }

    @GetMapping(value = "customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplianceStatusDto> getApplianceStatusByCustomerId(@PathVariable("customerId") String customerId) {
        LOGGER.info("Get /appliance status for customer {}", customerId);
        List<ApplianceStatus> applianceStatusList = (applianceStatusService.getApplianceStatusByCustomerId(customerId));
        List<ApplianceStatusDto> applianceStatusDtos=  applianceStatusList.stream()
                .map(applianceStatusMapper::convertToDto)
                .collect(Collectors.toList());

        return applianceStatusDtos;
    }

    @PostMapping(value = "/{customerId}/{applianceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplianceStatusDto createApplianceStatus
            (@PathVariable("customerId") String customerId, @PathVariable("applianceId") String applianceId) {
        LOGGER.info("CREATE /appliance status");
        return applianceStatusMapper.convertToDto(applianceStatusService.createApplianceStatus(customerId,applianceId));
    }

    @PutMapping(value = "/ping/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApplianceStatusDto handleAppliancePing(@PathVariable("id") Integer id) {
        LOGGER.info("UPDATE /appliance status/{}", id);
        return applianceStatusMapper.convertToDto(applianceStatusService.updateStatusForAppliance(id, Status.up));

    }



}
