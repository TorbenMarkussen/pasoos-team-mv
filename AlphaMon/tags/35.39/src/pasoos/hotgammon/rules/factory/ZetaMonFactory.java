package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.rules.boards.HybergammonBoardInitializer;

public class ZetaMonFactory extends AlphaMonFactory {
    @Override
    public Board createBoard() {
        return new BoardImpl(new HybergammonBoardInitializer());
    }
}
