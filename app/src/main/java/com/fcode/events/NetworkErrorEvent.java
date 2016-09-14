package com.fcode.events;

/**
 * The event that is posted when a network error event occurs.
 * TODO: Consume this event in the {@link com.fcode.ui.BootstrapActivity} and
 * show a dialog that something went wrong.
 */
public class NetworkErrorEvent {

    private Throwable cause;

    public NetworkErrorEvent(Throwable cause) {
        this.cause = cause;
    }

    public Throwable getCause() {
        return cause;
    }
}
