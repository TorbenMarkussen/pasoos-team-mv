package paystation.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 24-08-12
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
public class DKKValidatorStrategyImpl implements CoinValidatorStrategy {
    List<Integer> validCoins = new ArrayList<Integer>(Arrays.asList(1, 2, 5, 10, 20));

    @Override
    public void validate(int coinValue) throws IllegalCoinException {
        if (!validCoins.contains(coinValue))
            throw new IllegalCoinException("");
    }
}
