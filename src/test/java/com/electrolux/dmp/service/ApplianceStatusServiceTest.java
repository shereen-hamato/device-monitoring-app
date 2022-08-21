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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplianceStatusServiceTest {

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
    private static Timestamp LAST_MODIFIED = new Timestamp(System.currentTimeMillis());
    private Address address = new Address(ADDRESS_LINE1, null, ZIP, CITY, COUNTRY);
    private Customer customer = new Customer(CUSTOMER_ID, FIRST_NAME, LAST_NAME, address);
    private Appliance appliance = new Appliance(APPLIANCE_ID, FACTORY_ID);
    private ApplianceStatusDto applianceStatusDto = new ApplianceStatusDto(appliance.getApplianceId(), customer.getCustomerId(), STATUS, LAST_MODIFIED);
    private ApplianceStatus applianceStatus = new ApplianceStatus(appliance, customer, STATUS, LAST_MODIFIED);
    private static final int APPLIANCE_STATUS_ID = 998;
    @InjectMocks
    private ApplianceStatusService applianceStatusService;

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private AddressRepo addressRepo;

    @Mock
    private ApplianceRepo applianceRepo;

    @Mock
    private ApplianceStatusRepo applianceStatusRepo;


    @Before
    public void setupReturnValuesOfMockMethods() {
        customer.setId(1);
        appliance.setId(1);
        when(applianceStatusRepo.findById(APPLIANCE_STATUS_ID)).thenReturn(Optional.of(applianceStatus));
        when(customerRepo.findByCustomerId(customer.getCustomerId())).thenReturn(Optional.of(customer));
        when(applianceRepo.findByApplianceId(appliance.getApplianceId())).thenReturn(Optional.of(appliance));
    }

    @Test
    public void testGetApplianceStatusList() {
        applianceStatusService.getApplianceStatusList();
        verify(applianceStatusRepo).findAll();
    }

    @Test
    public void testCreateApplianceStatus() {
        ArgumentCaptor<ApplianceStatus> applianceStatusCaptor = ArgumentCaptor.forClass(ApplianceStatus.class);
        applianceStatusService.createApplianceStatus(CUSTOMER_ID, APPLIANCE_ID);

        verify(applianceStatusRepo).save(applianceStatusCaptor.capture());
        assertEquals(applianceStatusCaptor.getValue().getAppliance().getId(), appliance.getId());
        assertEquals(applianceStatusCaptor.getValue().getCustomer().getId(), customer.getId());
        assertEquals(applianceStatusCaptor.getValue().getStatus(), Status.up.name());
    }

    @Test
    public void testGetApplianceStatusById() {
        applianceStatusService.getApplianceStatusById(APPLIANCE_STATUS_ID);

        verify(applianceStatusRepo).findById(APPLIANCE_STATUS_ID);
    }

    @Test
    public void testUpdateApplianceStatus() {
        ArgumentCaptor<ApplianceStatus> ApplianceStatusCaptor = ArgumentCaptor.forClass(ApplianceStatus.class);

        applianceStatusService.updateStatusForAppliance(APPLIANCE_STATUS_ID, Status.up);

        verify(applianceStatusRepo).save(ApplianceStatusCaptor.capture());
        assertEquals(ApplianceStatusCaptor.getValue().getStatus(), Status.up.name());
    }


}