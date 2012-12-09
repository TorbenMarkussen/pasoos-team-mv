package pasoos.hotgammon.rules;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pasoos.hotgammon.rules.diceroller.TestRandomDiceRoller;
import pasoos.hotgammon.rules.factory.SemiMonFactoryTest;
import pasoos.hotgammon.rules.validator.AllValidatorTests;
import pasoos.hotgammon.rules.winner.AllWinnerTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllValidatorTests.class,
        SemiMonFactoryTest.class,
        TestRandomDiceRoller.class,
        AllValidatorTests.class,
        AllWinnerTests.class})
public class AllRulesTests {
}
