package com.tui.proof.ws.event;

/**
 * Author: Janty
 */
public class BookingEvent extends Event {
    /**
     *
     * @param key
     * @param source
     * @param operation
     */
    public BookingEvent(String key, Object source, String operation) {
        super(source);
        addHeader("operation", operation);
        addHeader("messageKey", key.getBytes());
    }
}
