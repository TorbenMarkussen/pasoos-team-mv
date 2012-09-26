package pasoos.hotgammon.rules;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.gameengine.GameState;

public interface WinnerStrategy {
    Color determineWinner(GameState gameState);
}
