package paystation.client;

import paystation.common.StatusEvent;
import paystation.common.StatusListener;

import javax.swing.*;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A StatusListener that may update the status frame's lables.
 */
public class LabelUpdater implements StatusListener {
    private JLabel vLabel, eLabel;
    private int sum;
    private int vacant;

    public LabelUpdater(JLabel vacantLabel, JLabel earnedLabel) {
        vLabel = vacantLabel;
        eLabel = earnedLabel;
        sum = 0;
        vacant = 200;
    }

    public synchronized void update(StatusEvent e) {

        sum += e.earned;
        vacant--;
        // we have to do something clever to calculate the number
        // of vacant parking lots: decrease the number of
        // vacant slots by one, and increment it once the
        // clock has passed how many minutes this parking
        // will take - however, it is irrelevant for the
        // present exercise and I just 'fake it' here...
        eLabel.setText("  Total Earning " + sum);
        vLabel.setText("  Number of vacant: " + vacant);

    }
}
