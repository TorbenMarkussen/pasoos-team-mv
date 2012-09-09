package pasoos.hotgammon;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.rules.DiceRoller;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.RulesFactory;
import pasoos.hotgammon.rules.WinnerStrategy;
import pasoos.hotgammon.rules.validator.alphamon.AlphamonMoveValidatorStrategyImpl;
import pasoos.hotgammon.rules.winner.simple.SimpleWinnerStrategyImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestEpsilonMon {
    private Game game;
    private DiceRoller stubbedDiceRoller;

    @Before
    public void setup() {
        stubbedDiceRoller = mock(DiceRoller.class);

        RulesFactory ruleFactory = new RulesFactory() {
            @Override
            public MoveValidatorStrategy createMoveValidatorStrategy(Board board) {
                return new AlphamonMoveValidatorStrategyImpl(board);
            }

            @Override
            public WinnerStrategy createWinnerStrategy() {
                return new SimpleWinnerStrategyImpl();
            }

            @Override
            public DiceRoller createDiceRoller() {
                return stubbedDiceRoller;
            }

            @Override
            public Board createBoard() {
                return new BoardImpl();
            }
        };

        game = GameFactory.createGame(ruleFactory);
    }

    @Test
    public void game_should_use_dice_roller_stub() {
        when(stubbedDiceRoller.roll()).thenReturn(new int[]{4, 2});
        game.nextTurn();
        assertEquals(2, game.diceValuesLeft().length);
        assertEquals(4, game.diceValuesLeft()[0]);
        assertEquals(2, game.diceValuesLeft()[1]);


        when(stubbedDiceRoller.roll()).thenReturn(new int[]{3, 6});
        game.nextTurn();
        assertEquals(6, game.diceValuesLeft()[0]);
        assertEquals(3, game.diceValuesLeft()[1]);

        verify(stubbedDiceRoller, times(2)).roll();
    }
}
