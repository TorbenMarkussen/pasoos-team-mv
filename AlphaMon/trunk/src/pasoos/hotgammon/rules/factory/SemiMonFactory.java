package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.DiceRoller;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.WinnerStrategy;
import pasoos.hotgammon.rules.boards.BackGammonBoardInitializer;
import pasoos.hotgammon.rules.diceroller.RandomDiceRoller;
import pasoos.hotgammon.rules.validator.BackgammonMoveValidatorStrategy;
import pasoos.hotgammon.rules.winner.BackgammonWinnerStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: sp
 * Date: 17-09-12
 * Time: 20:15
 */
public class SemiMonFactory implements HotGammonFactory {
    @Override
    public MoveValidatorStrategy createMoveValidatorStrategy(GameState gameState, Board board) {
        return new BackgammonMoveValidatorStrategy(board);
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new BackgammonWinnerStrategy();
    }

    @Override
    public DiceRoller createDiceRoller() {
        return new RandomDiceRoller();
    }

    @Override
    public Board createBoard() {
        return new BoardImpl(new BackGammonBoardInitializer());
    }
}
