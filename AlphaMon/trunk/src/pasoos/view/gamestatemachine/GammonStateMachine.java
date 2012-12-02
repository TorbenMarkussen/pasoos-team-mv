package pasoos.view.gamestatemachine;

import pasoos.hotgammon.Location;

public interface GammonStateMachine {
    void blackPlayerActive();

    void redPlayerActive();

    boolean moveRequest(Location from, Location to);

    void checkerMoved(Location from, Location to);

    void rollDiceRequest();

    void diceRolled(int[] values);

    void turnEnded();

    void winnerFound();

}
