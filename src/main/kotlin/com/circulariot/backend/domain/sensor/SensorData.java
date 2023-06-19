package com.circulariot.backend.domain.sensor;

import java.util.UUID;

public record SensorData(UUID id, Double x, Double y, Double z) {
}
