package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.rules.DiceRoller;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.RulesFactory;
import pasoos.hotgammon.rules.WinnerStrategy;
import pasoos.hotgammon.rules.boards.BackGammonBoardInitializerImpl;
import pasoos.hotgammon.rules.diceroller.FixedDiceRoller;
import pasoos.hotgammon.rules.validator.alphamon.AlphamonMoveValidatorStrategyImpl;
import pasoos.hotgammon.rules.winner.simple.SimpleWinnerStrategyImpl;

public class AlphaMonRules implements RulesFactory {
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
        return new FixedDiceRoller();
    }

    @Override
    public Board createBoard() {
        return new BoardImpl(new BackGammonBoardInitializerImpl());
    }
}
