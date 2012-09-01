package pasoos.hotgammon;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAlphamon.class,
        TestBetamon.class,
        TestLocation.class,
        pasoos.hotgammon.gameengine.validator.AllTests.class})
public class AllTests {

}
