package ar.edu.unq.desapp.groupb.builders;

import ar.edu.unq.desapp.groupb.model.Event;


public class EventBuilder  extends AbstractBuilder<Event> {

    @Override
    public Event anyObject() {
        return new Event();
    }

}