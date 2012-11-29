package pasoos.view;

import pasoos.hotgammon.Location;

public interface State {
    void blackPlayerActive();

    void redPlayerActive();

    void winnerFound();

    boolean moveRequest(Location from, Location to);

    void checkerMoved(Location from, Location to);

    void rollDiceRequest();

    void diceRolled(int[] values);

    void turnEnded();

    void onEntry();

    void onExit();

    StateId getStateId();
}
