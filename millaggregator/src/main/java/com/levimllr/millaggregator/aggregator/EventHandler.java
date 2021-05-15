package com.levimllr.millaggregator.aggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static com.levimllr.millaggregator.configuration.WebSocketConfiguration.MESSAGE_PREFIX;

@Component
@RepositoryEventHandler
public class EventHandler {

    private final SimpMessagingTemplate websocket;

    private final EntityLinks entityLinks;

    @Autowired
    public EventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
        this.websocket = websocket;
        this.entityLinks = entityLinks;
    }

    @HandleAfterCreate
    public void newAggregator(Aggregator aggregator) {
        this.websocket.convertAndSend(MESSAGE_PREFIX + "/newAggregator", getPath(aggregator));
    }

    @HandleAfterDelete
    public void deleteAggregator(Aggregator aggregator) {
        this.websocket.convertAndSend(MESSAGE_PREFIX + "/deleteAggregator", getPath(aggregator));
    }

    @HandleAfterSave
    public void updateAggregator(Aggregator aggregator) {
        this.websocket.convertAndSend(MESSAGE_PREFIX + "/updateAggregator", getPath(aggregator));
    }

    private String getPath(Aggregator aggregator) {
        return this.entityLinks.linkForItemResource(aggregator.getClass(), aggregator.getId()).toUri().getPath();
    }
}
