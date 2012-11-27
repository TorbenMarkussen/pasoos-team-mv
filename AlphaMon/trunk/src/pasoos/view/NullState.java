package pasoos.view;

import pasoos.hotgammon.Location;

public class NullState extends State {
    @Override
    public void blackPlayerActive() {
    }

    @Override
    public void redPlayerActive() {
    }

    @Override
    public void winnerFound() {
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return false;
    }

    @Override
    public void checkerMoved(Location from, Location to) {
    }

    @Override
    public void rollDiceRequest() {
    }

    @Override
    public void diceRolled(int[] values) {
    }

    @Override
    public void turnEnded() {
    }

    @Override
    public void onEntry() {
    }

    @Override
    public void onExit() {

    }
}
