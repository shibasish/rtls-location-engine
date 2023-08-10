package com.circulariot.backend.service;

import com.circulariot.backend.domain.sensor.SensorData;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AggregatorService {

    private final LocationService locationService;

    @ServiceActivator(inputChannel = "messageTransformedOutputChannel")
    public void aggregatedAnchorsForTag(SensorData sensorData) {
//        List<SensorData> sensors = new ArrayList<>();
//        sensors.add(sensorData);

        System.out.println("in aggregator service: "+ sensorData.name());
        locationService.predictLocation(sensorData);
    }
}
