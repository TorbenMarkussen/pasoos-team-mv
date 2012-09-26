package pasoos.hotgammon.rules;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pasoos.hotgammon.rules.validator.AllValidatorTests;
import pasoos.hotgammon.rules.winner.AllWinnerTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllValidatorTests.class,
        AllWinnerTests.class})
public class AllRulesTests {
}
