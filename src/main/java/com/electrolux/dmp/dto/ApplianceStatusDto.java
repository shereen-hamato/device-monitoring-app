package com.electrolux.dmp.dto;

import java.sql.Timestamp;

public class ApplianceStatusDto {
    private Integer id;
    private String applianceId;
    private String customerId;
    private String status;
    private Timestamp last_modified;

    public ApplianceStatusDto(String applianceId, String customerId, String status, Timestamp last_modified) {
        this.applianceId = applianceId;
        this.customerId = customerId;
        this.status = status;
        this.last_modified = last_modified;
    }

    public ApplianceStatusDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(String applianceId) {
        this.applianceId = applianceId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Timestamp last_modified) {
        this.last_modified = last_modified;
    }
}
