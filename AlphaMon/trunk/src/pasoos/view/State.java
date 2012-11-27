package pasoos.view;

import pasoos.hotgammon.Location;

public abstract class State {
    abstract public void blackPlayerActive();

    abstract public void redPlayerActive();

    abstract public void winnerFound();

    abstract public boolean moveRequest(Location from, Location to);

    abstract public void checkerMoved(Location from, Location to);

    abstract public void rollDiceRequest();

    abstract public void diceRolled(int[] values);

    abstract public void turnEnded();

    public abstract void onEntry();

    public abstract void onExit();
}
