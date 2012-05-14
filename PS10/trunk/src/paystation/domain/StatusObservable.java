package paystation.domain;

/**
 * The subject role of the observer pattern: a StatusObservable instance can be observed for pay station state changes.
 */

public interface StatusObservable {
    /** Add a status listener to this instance */
    public void addStatusListener(StatusListener listener);
}
