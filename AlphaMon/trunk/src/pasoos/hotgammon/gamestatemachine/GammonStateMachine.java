package pasoos.hotgammon.gamestatemachine;

import pasoos.hotgammon.Location;

public interface GammonStateMachine {

    boolean moveRequest(Location from, Location to);

    void rollDiceRequest();

    void blackPlayerActive();

    void redPlayerActive();

    void checkerMoved(Location from, Location to);

    void diceRolled(int[] values);

    void turnEnded();

    void winnerFound();

    void rackGame();
}
