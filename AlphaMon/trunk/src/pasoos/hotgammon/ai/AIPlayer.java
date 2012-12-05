package pasoos.hotgammon.ai;

import pasoos.hotgammon.obsolete.minidraw_controller.GammonMove;

import java.util.List;

public interface AIPlayer {
    void play();

    List<GammonMove> getMoves();
}
