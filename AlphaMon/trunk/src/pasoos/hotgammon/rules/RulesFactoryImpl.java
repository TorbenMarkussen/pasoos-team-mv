package pasoos.hotgammon.rules;

import pasoos.hotgammon.rules.boards.BackGammonBoardStrategyImpl;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.HotGammonTypes;
import pasoos.hotgammon.rules.boards.HybergammonBoardStrategyImpl;
import pasoos.hotgammon.rules.diceroller.FixedDiceRoller;
import pasoos.hotgammon.rules.diceroller.RandomDiceRoller;
import pasoos.hotgammon.rules.validator.alphamon.AlphamonMoveValidatorStrategyImpl;
import pasoos.hotgammon.rules.validator.betamon.BetamonMoveValidatorStrategyImpl;
import pasoos.hotgammon.rules.winner.gammamon.GammamonWinnerStrategyImpl;
import pasoos.hotgammon.rules.winner.simple.SimpleWinnerStrategyImpl;

public class RulesFactoryImpl implements RulesFactory {
    private HotGammonTypes hotGammonType;

    public RulesFactoryImpl(HotGammonTypes type) {
        hotGammonType = type;
    }

    @Override
    public MoveValidatorStrategy getMoveValidatorStrategy(Board board) {
        if (hotGammonType == HotGammonTypes.BetaMon)
            return new BetamonMoveValidatorStrategyImpl(board);
        else
            return new AlphamonMoveValidatorStrategyImpl(board);

    }

    @Override
    public WinnerStrategy getWinnerStrategy() {
        if (hotGammonType == HotGammonTypes.GammaMon)
            return new GammamonWinnerStrategyImpl();
        else
            return new SimpleWinnerStrategyImpl();
    }

    @Override
    public DiceRoller getDiceRoller() {
        if (hotGammonType == HotGammonTypes.EpsilonMon)
            return new RandomDiceRoller();
        return new FixedDiceRoller();
    }

    @Override
    public Board getBoard() {
        if (hotGammonType == HotGammonTypes.ZetaMon)
            return new Board(new HybergammonBoardStrategyImpl());

        return new Board(new BackGammonBoardStrategyImpl());
    }
}
