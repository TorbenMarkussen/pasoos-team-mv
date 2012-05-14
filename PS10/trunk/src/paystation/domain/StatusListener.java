package paystation.domain;

/**
 * The observer role of the observer pattern: a StatusListener instance are updated whenever a pay station changes state.
 * 
 * The protocol is a 'push' protocol in that the listener carries the state of the pay station as part of the update method call.
 */

public interface StatusListener {
    /** invoked whenever a paystation changes state. */
    public void update(StatusEvent event);
}
