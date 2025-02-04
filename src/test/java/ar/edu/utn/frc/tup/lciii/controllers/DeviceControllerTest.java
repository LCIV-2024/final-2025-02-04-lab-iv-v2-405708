package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceDTO;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.repositories.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.services.FinalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.net.URL;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(DeviceController.class)
@AutoConfigureWebMvc
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FinalService finalService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL = "/device";

    DeviceDTO deviceDTO;
    Device device;

    @BeforeEach
    void setUp() {
        deviceDTO = new DeviceDTO();
        deviceDTO.setOs("Windows");
        deviceDTO.setType(DeviceType.LAPTOP);
        deviceDTO.setMacAddress("11221");
        deviceDTO.setHostName("localhost");

        device = new Device();
        device.setOs("Windows");
        device.setMacAddress("11221");
        device.setHostName("localhost");
        device.setType(DeviceType.LAPTOP);
        device.setCreatedDate(LocalDateTime.now());

    }


    @Test
    void postDevice() {
//        when(finalService.createDevice(deviceDTO)).thenReturn(deviceDTO);
//
//        MockHttpServletResponse response = this.mockMvc.perform(post(URL)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(deviceDTO)))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(asJsonString(deviceDTO));


    }

    @Test
    void getDevicesByType() {
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}