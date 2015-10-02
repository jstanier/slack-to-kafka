package com.jstanier.slacktokafka;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SlackMessageHandler implements SlackMessagePostedListener {

    @Autowired
    private SlackMessageProcessor processor;

    @Override
    public void onEvent(SlackMessagePosted event, SlackSession session) {
        processor.process(event);
    }
}
