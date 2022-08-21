package com.electrolux.dmp.service;


import com.electrolux.dmp.costants.Status;
import com.electrolux.dmp.dto.ApplianceStatusDto;
import com.electrolux.dmp.model.Address;
import com.electrolux.dmp.model.Appliance;
import com.electrolux.dmp.model.ApplianceStatus;
import com.electrolux.dmp.model.Customer;
import com.electrolux.dmp.repository.AddressRepo;
import com.electrolux.dmp.repository.ApplianceRepo;
import com.electrolux.dmp.repository.ApplianceStatusRepo;
import com.electrolux.dmp.repository.CustomerRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ApplianceStatusServiceIntegrationTest {

    private static final String CUSTOMER_ID = "customerId";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";

    private static final String ADDRESS_LINE1 = "address";
    private static final String ZIP = "zip";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";

    private static final String APPLIANCE_ID = "applianceId";
    private static final String FACTORY_ID = "factoryId";

    private static final String STATUS = "status";
    private static Timestamp LAST_MODIFIED;
    private Address address;
    private Customer customer;
    private Appliance appliance;
    private ApplianceStatusDto applianceStatusDto;

    @Autowired
    private ApplianceStatusService applianceStatusService;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ApplianceRepo applianceRepo;

    @Autowired
    private ApplianceStatusRepo applianceStatusRepo;

    @Before
    public void setupReturnValuesOfMockMethods() {
        address = new Address(ADDRESS_LINE1, null, ZIP, CITY, COUNTRY);
        addressRepo.save(address);

        customer = new Customer(CUSTOMER_ID, FIRST_NAME, LAST_NAME, address);
        customer = customerRepo.save(customer);

        appliance = new Appliance(APPLIANCE_ID, FACTORY_ID);
        appliance = applianceRepo.save(appliance);

        LAST_MODIFIED = new Timestamp(System.currentTimeMillis());
        applianceStatusDto = new ApplianceStatusDto(appliance.getApplianceId(), customer.getCustomerId(), STATUS, LAST_MODIFIED);

    }

    @Test
    public void testGetApplianceStatussList() {
        applianceStatusRepo.save(new ApplianceStatus(appliance, customer, STATUS, LAST_MODIFIED));
        applianceStatusRepo.save(new ApplianceStatus(appliance, customer, STATUS, LAST_MODIFIED));
        applianceStatusRepo.save(new ApplianceStatus(appliance, customer, STATUS, LAST_MODIFIED));

        List<ApplianceStatus> applianceStatusList = applianceStatusService.getApplianceStatusList();
        assertTrue(!applianceStatusList.isEmpty());
    }

    @Test
    public void testCreateApplianceStatus() {
        ApplianceStatus applianceStatus = applianceStatusService.createApplianceStatus(CUSTOMER_ID, APPLIANCE_ID );

        ApplianceStatus applianceStatusCreated = applianceStatusRepo.findById(applianceStatus.getId()).orElseThrow(() -> new NoSuchElementException());

        assertEquals(applianceStatusCreated.getAppliance().getId(), appliance.getId());
        assertEquals(applianceStatusCreated.getCustomer().getId(), customer.getId());
        assertEquals(applianceStatusCreated.getStatus(), applianceStatus.getStatus());
        assertEquals(applianceStatusCreated.getLast_modified(), applianceStatus.getLast_modified());
    }

    @Test
    public void testGetApplianceStatusById() {
        ApplianceStatus applianceStatus = applianceStatusRepo.save(new ApplianceStatus(appliance, customer, STATUS, LAST_MODIFIED));

        ApplianceStatus createdApplianceStatus = applianceStatusService.getApplianceStatusById(applianceStatus.getId());

        assertNotNull(createdApplianceStatus);
    }

    @Test
    public void testUpdateApplianceStatus() {
        ApplianceStatus applianceStatus = applianceStatusRepo.save(new ApplianceStatus(appliance, customer, STATUS, LAST_MODIFIED));

        applianceStatus.setStatus("new Status");
        applianceStatus.setLast_modified(new Timestamp(System.currentTimeMillis()));
        ApplianceStatus createdApplianceStatus = applianceStatusService.updateStatusForAppliance(applianceStatus.getId(), Status.up);

        assertNotNull(createdApplianceStatus);
        assertEquals(createdApplianceStatus.getStatus(), Status.up.name());
    }

}