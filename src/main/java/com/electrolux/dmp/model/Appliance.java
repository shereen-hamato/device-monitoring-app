package com.electrolux.dmp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "appliance_id", nullable = false)
    private String applianceId;

    @Column(name = "factory_id", nullable = false)
    private String factoryId;

    @OneToMany(mappedBy = "appliance")
    private Set<ApplianceStatus> applianceStatusSet;

    public Appliance(String applianceId, String factoryId) {
        this.applianceId = applianceId;
        this.factoryId = factoryId;
    }

    public Appliance() {
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

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public Set<ApplianceStatus> getApplianceStatusSet() {
        return applianceStatusSet;
    }

    public void setApplianceStatusSet(Set<ApplianceStatus> applianceStatusSet) {
        this.applianceStatusSet = applianceStatusSet;
    }
}
