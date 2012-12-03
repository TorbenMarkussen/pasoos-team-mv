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

    /*public Board createBoard() {
        return new BoardImpl(
        new BoardInitializerStrategy() {
            @Override
            public void initialize(InitializableBoard board) {
                board.clear();
                board.set(Location.R1, Color.RED, 1);
                board.set(Location.R2, Color.RED, 1);
                board.set(Location.R3, Color.RED, 1);
                board.set(Location.R4, Color.RED, 1);
                board.set(Location.R5, Color.RED, 1);
                board.set(Location.R6, Color.RED, 1);
                board.set(Location.B_BAR, Color.BLACK, 2);
            }
        });
    } */
}
