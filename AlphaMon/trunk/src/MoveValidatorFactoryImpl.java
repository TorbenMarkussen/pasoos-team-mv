/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */

enum HotGammonTypes {
    AlphaMon,
    BetaMon
}

public class MoveValidatorFactoryImpl implements MoveValidatorFactory {
    private HotGammonTypes hotGammonType;

    MoveValidatorFactoryImpl(HotGammonTypes type) {
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
