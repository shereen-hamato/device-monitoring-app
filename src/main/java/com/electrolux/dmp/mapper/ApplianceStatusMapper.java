package com.electrolux.dmp.mapper;

import com.electrolux.dmp.dto.ApplianceStatusDto;
import com.electrolux.dmp.model.ApplianceStatus;
import org.springframework.stereotype.Component;

@Component
public class ApplianceStatusMapper {
    public ApplianceStatusDto convertToDto(ApplianceStatus applianceStatus) {

        ApplianceStatusDto applianceStatusDto =new ApplianceStatusDto();
        applianceStatusDto.setId(applianceStatus.getId());
        applianceStatusDto.setApplianceId(applianceStatus.getAppliance().getApplianceId());
        applianceStatusDto.setCustomerId(applianceStatus.getCustomer().getCustomerId());
        applianceStatusDto.setStatus(applianceStatus.getStatus());
        applianceStatusDto.setLast_modified(applianceStatus.getLast_modified());

        return applianceStatusDto;
    }
}
