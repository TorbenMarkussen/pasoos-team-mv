package pasoos.hotgammon;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pasoos.hotgammon.gameengine.validator.AllValidatorTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAlphamon.class,
        TestBetamon.class,
        TestLocation.class,
        AllValidatorTests.class})
public class AllTests {

}
