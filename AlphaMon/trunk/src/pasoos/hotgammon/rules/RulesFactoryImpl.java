package pasoos.hotgammon.rules;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.HotGammonTypes;
import pasoos.hotgammon.gameengine.validator.MoveValidatorStrategy;
import pasoos.hotgammon.gameengine.validator.alphamon.AlphamonMoveValidatorStrategyImpl;
import pasoos.hotgammon.gameengine.validator.betamon.BetamonMoveValidatorStrategyImpl;
import pasoos.hotgammon.gameengine.winner.WinnerStrategy;
import pasoos.hotgammon.gameengine.winner.gammamon.GammamonWinnerStrategyImpl;
import pasoos.hotgammon.gameengine.winner.simple.SimpleWinnerStrategyImpl;
import pasoos.hotgammon.rules.RulesFactory;

public class RulesFactoryImpl implements RulesFactory {
    private HotGammonTypes hotGammonType;

    public RulesFactoryImpl(HotGammonTypes type) {
        hotGammonType = type;
    }

    @Override
    public MoveValidatorStrategy GetMoveValidatorStrategy(Board board) {
        if (hotGammonType == HotGammonTypes.BetaMon)
            return new BetamonMoveValidatorStrategyImpl(board);
        else
            return new AlphamonMoveValidatorStrategyImpl(board);

    }

    @Override
    public WinnerStrategy GetWinnerStrategy(Board board) {
        if (hotGammonType == HotGammonTypes.Gammamon)
            return new GammamonWinnerStrategyImpl(board);
        else
            return new SimpleWinnerStrategyImpl(board);

    }
}
