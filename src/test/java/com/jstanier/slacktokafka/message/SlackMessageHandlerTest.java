package com.jstanier.slacktokafka.message;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SlackMessageHandlerTest {

    @Mock
    private SlackMessageProcessor processor;

    @Mock
    private SlackSession session;

    @Mock
    private SlackMessagePosted message;

    @InjectMocks
    private SlackMessageHandler handler;

    @Test
    public void whenHandlerIsCalled_thenEventIsProcessed() {
        handler.onEvent(message, session);
        Mockito.verify(processor, Mockito.times(1)).process(message);
    }
}
