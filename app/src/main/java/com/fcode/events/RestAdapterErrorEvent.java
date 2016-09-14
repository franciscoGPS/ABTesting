package com.fcode.events;



/**
 * Error that is posted when a non-network error event occurs in the {@link retrofit2}
 */
public class RestAdapterErrorEvent {
    private Throwable cause;

    public RestAdapterErrorEvent(Throwable cause) {
        this.cause = cause;
    }

    public Throwable getCause() {
        return cause;
    }
}
