package com.circulariot.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final ServerSentEventService serverSentEventService;


    public void predictLocation(String sensorDataList) {
        //predict location
        //implement tsdb repository here
        //send to websocket
//        serverSentEventService.sendEvent(SensorData.builder().name(sensorDataList.name()).build().toString());
        serverSentEventService.sendEvent(sensorDataList);
    }

}
