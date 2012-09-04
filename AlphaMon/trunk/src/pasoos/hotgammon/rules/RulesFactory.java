package pasoos.hotgammon.rules;

import pasoos.hotgammon.gameengine.Board;

public interface RulesFactory {
    MoveValidatorStrategy getMoveValidatorStrategy(Board board);

    WinnerStrategy getWinnerStrategy();

    DiceRoller getDiceRoller();

    Board getBoard();
}
