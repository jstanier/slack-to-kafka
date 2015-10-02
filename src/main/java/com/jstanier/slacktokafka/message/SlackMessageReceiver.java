package com.jstanier.slacktokafka.message;

import com.ullink.slack.simpleslackapi.SlackSession;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SlackMessageReceiver {

    @Autowired
    private SlackSession session;

    @Autowired
    private ObjectFactory<SlackMessageHandler> handlerFactory;

    @PostConstruct
    public void start() {
        session.addMessagePostedListener(handlerFactory.getObject());
    }
}
