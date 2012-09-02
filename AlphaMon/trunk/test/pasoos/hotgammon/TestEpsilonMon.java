package pasoos.hotgammon;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.rules.DiceRoller;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.RulesFactory;
import pasoos.hotgammon.rules.WinnerStrategy;
import pasoos.hotgammon.rules.validator.alphamon.AlphamonMoveValidatorStrategyImpl;
import pasoos.hotgammon.rules.winner.simple.SimpleWinnerStrategyImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestEpsilonMon {
    private Game game;
    private DiceRoller stuppedDiceRoller;

    @Before
    public void setup() {
        stuppedDiceRoller = mock(DiceRoller.class);

        RulesFactory ruleFactory = new RulesFactory() {
            @Override
            public MoveValidatorStrategy getMoveValidatorStrategy(Board board) {
                return new AlphamonMoveValidatorStrategyImpl(board);
            }

            @Override
            public WinnerStrategy getWinnerStrategy(Board board) {
                return new SimpleWinnerStrategyImpl();
            }

            @Override
            public DiceRoller getDiceRoller() {
                return stuppedDiceRoller;
            }
        };

        game = GameFactory.Get(ruleFactory);
    }

    @Test
    public void game_should_use_dice_roller_stub() {
        when(stuppedDiceRoller.roll()).thenReturn(new int[]{4, 2});
        game.nextTurn();
        assertEquals(4, game.diceValuesLeft()[0]);
        assertEquals(2, game.diceValuesLeft()[1]);

        when(stuppedDiceRoller.roll()).thenReturn(new int[]{3, 6});
        game.nextTurn();
        assertEquals(6, game.diceValuesLeft()[0]);
        assertEquals(3, game.diceValuesLeft()[1]);
    }
}
