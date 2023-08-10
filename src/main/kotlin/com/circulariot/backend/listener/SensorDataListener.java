package com.circulariot.backend.listener;

//@Component
public class SensorDataListener {

//    private final FluxProcessor<String, String> processor =
//            DirectProcessor.<String>create().serialize();

//    private final LocationService measurementService;
//
//    public SensorDataListener(@Qualifier("mqttInputChannel") FluxMessageChannel channel,
//                              LocationService measurementService) {
////        Flux.from(channel)
////                .filter(s -> s.getPayload() instanceof SensorData);
//
//        this.measurementService = measurementService;
//
//        channel.subscribe(
//                message -> {
//                    measurementService.predictLocation();
//                });
////                err -> System.out.println(err));
//    }

//    @Bean
//    @ServiceActivator(inputChannel = "mqttInputChannel")
//    public MessageHandler inbound() {
//        return message -> System.out.println("Received message: " + message.getPayload());
//    }
}
