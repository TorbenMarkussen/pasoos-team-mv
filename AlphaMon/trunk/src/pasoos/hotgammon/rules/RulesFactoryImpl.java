package pasoos.hotgammon.rules;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.HotGammonTypes;
import pasoos.hotgammon.rules.diceroller.FixedDiceRoller;
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
    public WinnerStrategy getWinnerStrategy(Board board) {
        if (hotGammonType == HotGammonTypes.GammaMon)
            return new GammamonWinnerStrategyImpl(board);
        else
            return new SimpleWinnerStrategyImpl();
    }

    @Override
    public DiceRoller getDiceRoller() {
        return new FixedDiceRoller();
    }
}
