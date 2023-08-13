package com.circulariot.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AggregatorService {

    private final LocationService locationService;

    @ServiceActivator(inputChannel = "messageTransformedOutputChannel")
    public void aggregatedAnchorsForTag(String sensorData) {
//        List<SensorData> sensors = new ArrayList<>();
//        sensors.add(sensorData);

        System.out.println("in aggregator service: "+ sensorData);
        locationService.predictLocation(sensorData);
    }
}
