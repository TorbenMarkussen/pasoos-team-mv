package pasoos.hotgammon;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pasoos.hotgammon.rules.AllRulesTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAlphamon.class,
        TestBetamon.class,
        TestBetamonCustomBoard.class,
        TestEpsilonMon.class,
        TestGameObserver.class,
        TestGammamon.class,
        TestHandiMon.class,
        TestLocation.class,
        TestLogAlphaMon.class,
        TestZetaMon.class,
        AllRulesTests.class
})
public class AllTests {

}
