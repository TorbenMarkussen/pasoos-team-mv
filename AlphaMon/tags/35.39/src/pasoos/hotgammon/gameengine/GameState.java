package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;

public interface GameState {
    public int getTurnCount();

    boolean allCheckersOnBearOff(Color color);

    Color getPlayerInTurn();
}
