package pasoos.view;

import pasoos.hotgammon.minidraw_controller.GammonMove;

import java.util.List;

public interface AIPlayer {
    void play();

    List<GammonMove> getMoves();
}
