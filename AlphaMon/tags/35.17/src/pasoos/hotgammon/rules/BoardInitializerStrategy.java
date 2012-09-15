package pasoos.hotgammon.rules;

import pasoos.hotgammon.gameengine.InitializableBoard;


public interface BoardInitializerStrategy {
    void initialize(InitializableBoard board);
}
