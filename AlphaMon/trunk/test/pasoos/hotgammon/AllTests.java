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
        TestGammamon.class,
        TestLocation.class,
        AllRulesTests.class})
public class AllTests {

}
