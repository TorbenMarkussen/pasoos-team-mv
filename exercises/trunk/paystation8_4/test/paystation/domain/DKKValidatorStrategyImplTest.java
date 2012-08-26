package paystation.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 24-08-12
 * Time: 13:06
 * To change this template use File | Settings | File Templates.
 */
public class DKKValidatorStrategyImplTest {
    private CoinValidatorStrategy cvs;

    @Before
    public void setUp() {
        cvs = new DKKValidatorStrategyImpl();
    }

    @Test
    public void should_not_throw_exception_for_valid_coins() throws Exception {
        boolean didThrowException = false;

        try {
            cvs.validate(1);
            cvs.validate(2);
            cvs.validate(5);
            cvs.validate(10);
            cvs.validate(20);

        } catch (IllegalCoinException ice) {
            didThrowException = true;
        }
        assertEquals("should not throw exception", false, didThrowException);

    }

    @Test
    public void should_throw_exception_for_invalid_coins() throws Exception {
        boolean didThrowException = false;

        try {
            cvs.validate(25);
        } catch (IllegalCoinException ice) {
            didThrowException = true;
        }
        assertEquals("should throw exception", true, didThrowException);
    }
}
