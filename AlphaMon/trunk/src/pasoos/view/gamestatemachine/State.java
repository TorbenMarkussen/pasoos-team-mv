package pasoos.view.gamestatemachine;

import pasoos.hotgammon.Location;

public interface State {
    void blackPlayerActive();

    void redPlayerActive();

    boolean moveRequest(Location from, Location to);

    void checkerMoved(Location from, Location to);

    void rollDiceRequest();

    void diceRolled(int[] values);

    void turnEnded();

    void winnerFound();

    void onEntry();

    void onExit();

    StateId getStateId();
}
