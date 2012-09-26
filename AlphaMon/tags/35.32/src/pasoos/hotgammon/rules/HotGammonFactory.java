package pasoos.hotgammon.rules;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.GameState;

public interface HotGammonFactory {
    MoveValidatorStrategy createMoveValidatorStrategy(GameState gameState, Board board);

    WinnerStrategy createWinnerStrategy();

    DiceRoller createDiceRoller();

    Board createBoard();
}
