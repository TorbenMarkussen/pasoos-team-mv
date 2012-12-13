package pasoos.hotgammon.ai;

import java.util.List;

public interface AIPlayer {
    void play();

    List<GammonMove> getMoves();
}
