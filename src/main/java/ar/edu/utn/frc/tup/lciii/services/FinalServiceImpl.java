package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.dtos.common.AllTelemetryDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.EndpointDevices;
import ar.edu.utn.frc.tup.lciii.dtos.common.TelemetryDTO;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.repositories.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.repositories.TelemetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinalServiceImpl implements FinalService {
    @Autowired
    private TelemetryRepository telemetryRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private RestClient restClient;


    @Override
    public TelemetryDTO createTelemetry(TelemetryDTO telemetry) {

        Device device = deviceRepository.getDeviceByHostName(telemetry.getHostname());
        if(device == null) {
            return null; //Error por nombre no existente
        }
        Telemetry finalTelemetry = new Telemetry();
        finalTelemetry.setIp(telemetry.getIp());
        finalTelemetry.setDevice(device);
        finalTelemetry.setHostname(device.getHostName());
        finalTelemetry.setCpuUsage(telemetry.getCpuUsage());
        finalTelemetry.setHostDiskFree(telemetry.getHostDiskFree());
        finalTelemetry.setMicrophoneState(telemetry.getMicrophoneState());
        finalTelemetry.setScreenCaptureAllowed(telemetry.getScreenCaptureAllowed());
        finalTelemetry.setAudioCaptureAllowed(telemetry.getAudioCaptureAllowed());
        finalTelemetry.setDataDate(telemetry.getDataDate());

        telemetryRepository.save(finalTelemetry);
        return telemetry;
    }

    @Override
    public DeviceDTO createDevice(DeviceDTO device) {

        Device deviceExists = deviceRepository.getDeviceByHostName(device.getHostName());
        if(deviceExists != null) {
            return null; //Error por nombre no existente
        }

        Device newDevice = new Device();
        newDevice.setHostName(device.getHostName());
        newDevice.setCreatedDate(LocalDateTime.now());
        newDevice.setType(device.getType());
        newDevice.setOs(device.getOs());
        newDevice.setMacAddress(device.getMacAddress());

        deviceRepository.save(newDevice);

        return device;
    }

    @Override
    public List<AllTelemetryDTO> getTelemetries(String hostname) {
        List<Telemetry> allTelemetries = new ArrayList<>();

        allTelemetries = telemetryRepository.findAll();

        List<Telemetry> telemetryFilter = new ArrayList<>();
        if(hostname != null && !hostname.isEmpty()) {
            for(Telemetry telemetry : allTelemetries) {
                if(telemetry.getHostname().equals(hostname)){
                    telemetryFilter.add(telemetry);
                }
            }
        }
        else telemetryFilter = allTelemetries;



        List<AllTelemetryDTO> allTelemetriesDTO = new ArrayList<>();
                for(Telemetry telemetry : telemetryFilter) {
                    AllTelemetryDTO telemetryDTO = new AllTelemetryDTO();
                    telemetryDTO.setIp(telemetry.getIp());
                    telemetryDTO.setHostname(telemetry.getHostname());
                    telemetryDTO.setMicrophoneState(telemetry.getMicrophoneState());
                    telemetryDTO.setScreenCaptureAllowed(telemetry.getScreenCaptureAllowed());
                    telemetryDTO.setAudioCaptureAllowed(telemetry.getAudioCaptureAllowed());
                    telemetryDTO.setDataDate(telemetry.getDataDate());
                    telemetryDTO.setHostDiskFree(telemetry.getHostDiskFree());

                    allTelemetriesDTO.add(telemetryDTO);
                }


        return allTelemetriesDTO;
    }

    @Override
    public List<DeviceDTO> getDevicesByType(String type) {

        List<Device> allDevices = deviceRepository.findAll();
        List<DeviceDTO> allDevicesDTO = new ArrayList<>();
        for(Device device : allDevices) {
            if(device.getType().toString().equalsIgnoreCase(type)) {
                DeviceDTO deviceDTO = new DeviceDTO();
                deviceDTO.setHostName(device.getHostName());
                deviceDTO.setOs(device.getOs());
                deviceDTO.setMacAddress(device.getMacAddress());
                deviceDTO.setType(device.getType());
                allDevicesDTO.add(deviceDTO);
            }
        }

        return allDevicesDTO;
    }

    @Override
    public List<DeviceDTO> getDevicesBetweenByConsumo(Double min, Double max) {

        if(min > max){
            return null;
        }

        //TODO Consumo de telemetria

        return null;
    }

    @Override
    public List<DeviceDTO> endpointInsert() {

        List<EndpointDevices> 


        return List.of();
    }
}
