package pasoos.view.gamestatemachine;

import minidraw.boardgame.BoardDrawing;
import minidraw.framework.Animation;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

public class StateContextImpl implements StateContext {

    private Game game;

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void setState(StateId stateId) {
    }

    @Override
    public State getState() {
        return null;
    }

    @Override
    public void updateStatus(State state, String message) {
    }

    @Override
    public BoardDrawing<Location> getBoardDrawing() {
        return null;
    }

    @Override
    public void startAnimation(Animation a, Location from, Location to) {
    }

    @Override
    public void rollDice() {
    }

    @Override
    public void notifyPieceMovedEvent(Location from, Location to) {
    }

    @Override
    public void notifyDiceRolled(int[] values) {
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }
}
