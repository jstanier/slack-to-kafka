package com.jstanier.slacktokafka.message;

import com.ullink.slack.simpleslackapi.SlackSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.ObjectFactory;

@RunWith(MockitoJUnitRunner.class)
public class SlackMessageReceiverTest {

    @Mock
    private SlackSession session;

    @Mock
    private ObjectFactory<SlackMessageHandler> handlerFactory;

    @InjectMocks
    private SlackMessageReceiver receiver;

    @Mock
    private SlackMessageHandler handler;

    @Test
    public void whenStarted_handlerIsAddedToSession() {
        Mockito.when(handlerFactory.getObject()).thenReturn(handler);
        receiver.start();
        Mockito.verify(session, Mockito.times(1)).addMessagePostedListener(handler);
    }
}
