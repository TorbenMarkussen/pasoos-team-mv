package pasoos.hotgammon.rules;

import pasoos.hotgammon.gameengine.Board;

public interface RulesFactory {
    MoveValidatorStrategy createMoveValidatorStrategy(Board board);

    WinnerStrategy createWinnerStrategy();

    DiceRoller createDiceRoller();

    Board createBoard();
}
