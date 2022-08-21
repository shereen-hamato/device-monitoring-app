package com.electrolux.dmp.controller;

import com.electrolux.dmp.constant.Status;
import com.electrolux.dmp.dto.ApplianceStatusDto;
import com.electrolux.dmp.mapper.ApplianceStatusMapper;
import com.electrolux.dmp.model.Address;
import com.electrolux.dmp.model.Appliance;
import com.electrolux.dmp.model.ApplianceStatus;
import com.electrolux.dmp.model.Customer;
import com.electrolux.dmp.service.ApplianceStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplianceStatusController.class)
public class ApplianceStatusControllerTest {
    private static final String CUSTOMER_ID = "customerId";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String ADDRESS_LINE1 = "address";
    private static final String ZIP = "zip";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String APPLIANCE_ID = "applianceId";
    private static final String FACTORY_ID = "factoryId";
    private static final String STATUS = "up";
    private static Timestamp LAST_MODIFIED = new Timestamp(System.currentTimeMillis());
    private Address address = new Address(ADDRESS_LINE1, null, ZIP, CITY, COUNTRY);
    private Customer customer = new Customer(CUSTOMER_ID, FIRST_NAME, LAST_NAME, address);
    private Appliance appliance = new Appliance(APPLIANCE_ID, FACTORY_ID);
    private ApplianceStatusDto applianceStatusDto = new ApplianceStatusDto(appliance.getApplianceId(), customer.getCustomerId(), STATUS, LAST_MODIFIED);
    private ApplianceStatus applianceStatus = new ApplianceStatus(appliance, customer, STATUS, LAST_MODIFIED);
    private static final int APPLIANCE_STATUS_ID = 998;
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ApplianceStatusService applianceStatusService;

    @MockBean
    private ApplianceStatusMapper modelMapper;


    @Test
    public void testPing() throws Exception {
        String uri = "/appliance-status/ping/" + APPLIANCE_STATUS_ID;
        when(applianceStatusService.updateStatusForAppliance(APPLIANCE_STATUS_ID, Status.up)).thenReturn(applianceStatus);
        when(modelMapper.convertToDto(applianceStatus)).thenReturn(applianceStatusDto);

        mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(applianceStatusDto.getStatus())))
                .andReturn().getResponse().getContentAsString();

    }

    @Test
    public void testGetApplianceStatusById() throws Exception {

        String uri = "/appliance-status/" + APPLIANCE_STATUS_ID;
        when(applianceStatusService.getApplianceStatusById(APPLIANCE_STATUS_ID)).thenReturn(applianceStatus);
        when(modelMapper.convertToDto(applianceStatus)).thenReturn(applianceStatusDto);

        mvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(applianceStatusDto.getStatus())))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testCreateApplianceStatus() throws Exception {
        String uri = "/appliance-status/"+CUSTOMER_ID+"/"+APPLIANCE_ID;
        when(applianceStatusService.createApplianceStatus(CUSTOMER_ID, APPLIANCE_ID )).thenReturn(applianceStatus);
        when(modelMapper.convertToDto(applianceStatus)).thenReturn(applianceStatusDto);

        mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status", is("up")))
                .andReturn().getResponse().getContentAsString();
    }


    public static String asJsonString(ApplianceStatusDto applianceStatusDto) {
        try {
            return new ObjectMapper().writeValueAsString(applianceStatusDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}