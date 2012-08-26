package paystation.domain;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 24-08-12
 * Time: 11:58
 * To change this template use File | Settings | File Templates.
 */
public interface CoinValidatorStrategy {
    void validate(int coinValue) throws IllegalCoinException;
}
