package paystation.domain;

import java.io.PrintStream;

/**
 * The receipt returned from a pay station. Responsibilities:
 * 
 * 1) Know the minutes parking time the receipt represents
 */
public interface Receipt {

    /**
     * Return the number of minutes this receipt is valid for.
     * 
     * @return number of minutes parking time
     */
    public int value();

    /**
     * Print a visual character-based representation of the receipt
     * 
     * @param the
     *            print stream to print the receipt on
     */
    public void print(PrintStream stream);
}
