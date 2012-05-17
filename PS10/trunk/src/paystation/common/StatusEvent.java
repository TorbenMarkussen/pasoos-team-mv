package paystation.common;

import java.io.Serializable;

/**
 * An event data object containing the status of a pay station sale.
 */

public class StatusEvent implements Serializable {
    /** construct a properly initialized status event object */
    public StatusEvent(int time, int earned) {
        this.time = time;
        this.earned = earned;
    }

    /**
     * the time in minutes left that the car will be parked on the parking lot.
     */
    public final int time;
    /**
     * the amount of cents that this pay station has earned by this sales of parking time.
     */
    public final int earned;
}
