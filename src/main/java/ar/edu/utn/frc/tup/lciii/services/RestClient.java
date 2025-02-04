package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.EndpointDevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RestClient {

    @Autowired
    private RestTemplate restTemplate;

    String url = "https://67a106a15bcfff4fabe171b0.mockapi.io/api/v1/device/device";

    public List<EndpointDevices> getDevices() {
        List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
        return response.stream().map(this::mapToDevices).collect(Collectors.toList());
    }

    private EndpointDevices mapToDevices(Map<String, Object> devices) {
        return EndpointDevices.builder()
                .id(((Number) devices.get("id")).longValue())
                .hostName((String) devices.get("hostName"))
                .createdDate((LocalDateTime) devices.get("createdDate"))
                .os((String) devices.get("os"))
                .macAddress((String) devices.get("macAddress"))
                .type((String) devices.get("type"))
                .build();
    }
}
