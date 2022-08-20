package com.electrolux.dmp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "appliance_status")
public class ApplianceStatus {

    public ApplianceStatus(Appliance appliance, Customer customer, String status, Timestamp last_modified) {
        this.appliance = appliance;
        this.customer = customer;
        this.status = status;
        this.last_modified = last_modified;
    }

    public ApplianceStatus() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "appliance_id")
    private Appliance appliance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "last_modified", nullable = false)
    private Timestamp last_modified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Appliance getAppliance() {
        return appliance;
    }

    public void setAppliance(Appliance appliance) {
        this.appliance = appliance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    @Override
    public String toString() {
        return "ApplianceStatus{" +
                "id=" + id +
                ", appliance=" + appliance +
                ", customer=" + customer +
                ", status='" + status + '\'' +
                ", last_modified=" + last_modified +
                '}';
    }
}
