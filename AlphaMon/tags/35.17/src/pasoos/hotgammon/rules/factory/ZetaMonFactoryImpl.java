package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.rules.boards.HybergammonBoardInitializerImpl;

public class ZetaMonFactoryImpl extends AlphaMonFactoryImpl {
    @Override
    public Board createBoard() {
        return new BoardImpl(new HybergammonBoardInitializerImpl());
    }
}
