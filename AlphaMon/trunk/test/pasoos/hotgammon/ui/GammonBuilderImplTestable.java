package pasoos.hotgammon.ui;

import minidraw.boardgame.BoardPiece;
import pasoos.hotgammon.gamestatemachine.GammonStateMachine;

public class GammonBuilderImplTestable extends GammonBuilderImpl {
    @Override
    protected BoardPiece createChecker(String figurename, GammonStateMachine gsc) {
        return super.createChecker(figurename, gsc);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected BoardPiece createDie(GammonDice dice) {
        return super.createDie(dice);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
