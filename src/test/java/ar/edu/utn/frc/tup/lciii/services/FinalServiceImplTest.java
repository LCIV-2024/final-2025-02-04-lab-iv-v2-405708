package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.AllTelemetryDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.TelemetryDTO;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.repositories.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.repositories.TelemetryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FinalServiceImplTest {

    @InjectMocks
    private FinalServiceImpl finalService;

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private TelemetryRepository telemetryRepository;

    DeviceDTO deviceDTO;
    Device device;
    Telemetry telemetry;
    TelemetryDTO telemetry2;
    AllTelemetryDTO allTelemetryDTO;

    List<TelemetryDTO> telemetryDTOList;
    List<AllTelemetryDTO> allTelemetryDTOList;

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

        telemetry = new Telemetry();
        telemetry.setDevice(device);
        telemetry.setHostname(device.getHostName());
        telemetry.setIp("127.0.0.1");
        telemetry.setCpuUsage(10.1);
        telemetry.setMicrophoneState("state");
        telemetry.setScreenCaptureAllowed(true);
        telemetry.setAudioCaptureAllowed(true);
        telemetry2 = new TelemetryDTO();
        telemetry2.setHostname(device.getHostName());
        telemetry2.setCpuUsage(10.1);
        telemetry2.setIp("127.0.0.1");
        telemetry2.setMicrophoneState("state");
        telemetry2.setScreenCaptureAllowed(true);
        telemetry2.setAudioCaptureAllowed(true);


        allTelemetryDTO = new AllTelemetryDTO();
        allTelemetryDTO.setHostname(device.getHostName());
        telemetryDTOList = new ArrayList<>();
        allTelemetryDTOList = new ArrayList<>();
        telemetryDTOList.add(telemetry2);
        allTelemetryDTOList.add(allTelemetryDTO);


    }


    @Test
    void createTelemetry() {
        when(deviceRepository.getDeviceByHostName("localhost")).thenReturn(device);
        when(telemetryRepository.save(any(Telemetry.class))).thenReturn(telemetry);

        TelemetryDTO telemetryDTO = finalService.createTelemetry(telemetry2);
        assertNotNull(telemetryDTO);
        assertEquals(telemetryDTO.getHostname(), telemetry.getHostname());
        assertEquals(telemetryDTO.getCpuUsage(), telemetry.getCpuUsage());
        assertEquals(telemetryDTO.getIp(), telemetry.getIp());
        assertEquals(telemetryDTO.getMicrophoneState(), telemetry.getMicrophoneState());
        assertEquals(telemetryDTO.getScreenCaptureAllowed(), telemetry.getScreenCaptureAllowed());
        assertEquals(telemetryDTO.getAudioCaptureAllowed(), telemetry.getAudioCaptureAllowed());
    }

    @Test
    void createDevice() {
        when(deviceRepository.save(any(Device.class))).thenReturn(device);
        when(telemetryRepository.save(any(Telemetry.class))).thenReturn(telemetry);

//        DeviceDTO deviceDTO = finalService.createDevice(deviceDTO);



    }

    @Test
    void getTelemetries() {





    }

    @Test
    void getDevicesByType() {
    }






}