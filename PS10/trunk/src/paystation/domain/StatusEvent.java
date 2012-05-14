package paystation.domain;

/**
 * An event data object containing the status of a pay station sale.
 */

public class StatusEvent {
    /** construct a properly initialized status event object */
    public StatusEvent(int time, int earned) {
        this.time = time;
        this.earned = earned;
    }

    /**
     * the time in minutes left that the car will be parked on the parking lot.
     */
    public int time;
    /**
     * the amount of cents that this pay station has earned by this sales of parking time.
     */
    public int earned;
}
