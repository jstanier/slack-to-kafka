package com.jstanier.slacktokafka;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class Config {

    @Value("${kafka.brokers}")
    private String kafkaBrokers;

    @Value("${slack.token}")
    private String slackToken;

    @Bean
    public SlackSession slackSession() throws IOException {
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession(slackToken);
        session.connect();
        return session;
    }

    @Bean
    public Producer<String, String> producer() {
        Properties props = new Properties();
        props.put("metadata.broker.list", kafkaBrokers);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        return new Producer<>(config);
    }
}
