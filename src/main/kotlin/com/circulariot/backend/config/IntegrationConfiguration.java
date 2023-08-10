package com.circulariot.backend.config;

import com.circulariot.backend.domain.sensor.SensorData;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.FluxMessageChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

@Configuration
public class IntegrationConfiguration {

    @Value("${mqtt.broker.url}")
    private String brokerUrl;

    @Value("${mqtt.client.id}")
    private String clientId;

    @Value("${mqtt.topic}")
    private String topic;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{brokerUrl});
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(clientId, mqttClientFactory(), topic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    public FluxMessageChannel messageTransformedOutputChannel() {
        return new FluxMessageChannel();
    }

    @Bean
    public FluxMessageChannel messageDistanceMeasurementChannel() {
        return new FluxMessageChannel();
    }

    @Bean
    public FluxMessageChannel mqttInputChannel() { return new FluxMessageChannel();}

    @Bean
    @Transformer(inputChannel="mqttInputChannel", outputChannel="messageTransformedOutputChannel")
    JsonToObjectTransformer jsonToObjectTransformer() {
        return new JsonToObjectTransformer(SensorData.class);
    }
}
