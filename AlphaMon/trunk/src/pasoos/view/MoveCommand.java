package pasoos.view;

import minidraw.boardgame.Command;
import pasoos.hotgammon.Location;
import pasoos.physics.Convert;
import pasoos.view.gamestatemachine.State;

public class MoveCommand implements Command {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    private State state;

    public MoveCommand(State state) {
        this.state = state;
    }

    @Override
    public void setFromCoordinates(int fromX, int fromY) {
        this.fromX = fromX;
        this.fromY = fromY;
    }

    @Override
    public void setToCoordinates(int toX, int toY) {
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public boolean execute() {
        Location from = Convert.xy2Location(fromX, fromY);
        Location to = Convert.xy2Location(toX, toY);
        return state.moveRequest(from, to);
    }
}
