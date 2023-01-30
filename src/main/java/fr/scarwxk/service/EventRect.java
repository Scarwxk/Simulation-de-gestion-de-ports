package fr.scarwxk.service;

import java.awt.Rectangle;

public class EventRect extends Rectangle {

    private int eventRectDefaultX, eventRectDefaultY;
    private boolean eventDone = false;


    public int getEventRectDefaultX() {
        return eventRectDefaultX;
    }

    public int getEventRectDefaultY() {
        return eventRectDefaultY;
    }

    public boolean isEventDone() {
        return eventDone;
    }

    public void setEventRectDefaultX(int eventRectDefaultX) {
        this.eventRectDefaultX = eventRectDefaultX;
    }

    public void setEventRectDefaultY(int eventRectDefaultY) {
        this.eventRectDefaultY = eventRectDefaultY;
    }

    public void setEventDone(boolean eventDone) {
        this.eventDone = eventDone;
    }
}
