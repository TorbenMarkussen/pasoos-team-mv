package pasoos.hotgammon.rules;

import pasoos.hotgammon.gameengine.Board;

public interface RulesFactory {
    MoveValidatorStrategy getMoveValidatorStrategy(Board board);

    WinnerStrategy getWinnerStrategy(Board board);

    DiceRoller getDiceRoller();

    Board getBoard();
}
