package paystation.domain;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 24-08-12
 * Time: 12:01
 * To change this template use File | Settings | File Templates.
 */

public class UsCentValidatorStrategyImpl implements CoinValidatorStrategy {

    @Override
    public void validate(int coinValue) throws IllegalCoinException {
        switch (coinValue) {
            case 5:
                break;
            case 10:
                break;
            case 25:
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
    }
}
