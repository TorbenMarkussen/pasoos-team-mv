package pasoos.hotgammon.gameengine.validator;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.HotGammonTypes;
import pasoos.hotgammon.gameengine.validator.alphamon.AlphamonMoveValidatorStrategyImpl;
import pasoos.hotgammon.gameengine.validator.betamon.BetamonMoveValidatorStrategyImpl;

public class MoveValidatorFactoryImpl implements MoveValidatorFactory {
    private HotGammonTypes hotGammonType;

    public MoveValidatorFactoryImpl(HotGammonTypes type) {
        hotGammonType = type;
    }

    @Override
    public MoveValidatorStrategy Get(Board board) {
        if (hotGammonType == HotGammonTypes.BetaMon)
            return new BetamonMoveValidatorStrategyImpl(board);
        else
            return new AlphamonMoveValidatorStrategyImpl(board);

    }
}
