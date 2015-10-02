package com.jstanier.slacktokafka.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SlackMessageProcessor {

    private static Logger logger = LoggerFactory.getLogger(SlackMessageProcessor.class);

    @Autowired
    private Producer<String, String> producer;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void process(SlackMessagePosted message) {
        try {
            String json = objectMapper.writeValueAsString(message);
            producer.send(new KeyedMessage<>(kafkaTopic, json));
        } catch (JsonProcessingException e) {
            logger.warn("Error converting message to JSON", e);
        }
    }
}
