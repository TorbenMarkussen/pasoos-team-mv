package paystation.server;

/**
 * Exception representing illegal coin entry.
 */
public class IllegalCoinException extends Exception {
    public IllegalCoinException(String e) {
        super(e);
    }
}
