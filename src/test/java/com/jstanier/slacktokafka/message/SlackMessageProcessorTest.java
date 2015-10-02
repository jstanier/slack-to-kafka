package com.jstanier.slacktokafka.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SlackMessageProcessorTest {

    @Mock
    private Producer<String, String> producer;

    @InjectMocks
    private SlackMessageProcessor processor;

    @Mock
    private SlackMessagePosted message;

    @Mock
    private ObjectMapper mapper;

    @Mock
    private JsonProcessingException jsonProcessingException;

    @Before
    public void setup() {
        Whitebox.setInternalState(processor, "kafkaTopic", "slack.messages");
        Whitebox.setInternalState(processor, "objectMapper", mapper);
    }

    @Test
    public void process_whenJSONConversionSucceeds_sendsMessageToKafka() throws JsonProcessingException {
        Mockito.when(mapper.writeValueAsString(Mockito.any())).thenReturn("It worked");
        processor.process(message);
        Mockito.verify(producer, Mockito.times(1)).send(Mockito.any(KeyedMessage.class));

    }

    @Test
    public void process_whenJSONConversionFails_nothingIsSentToKafka() throws JsonProcessingException {
        Mockito.when(mapper.writeValueAsString(Mockito.any())).thenThrow(jsonProcessingException);
        processor.process(message);
        Mockito.verifyNoMoreInteractions(producer);
    }
}
