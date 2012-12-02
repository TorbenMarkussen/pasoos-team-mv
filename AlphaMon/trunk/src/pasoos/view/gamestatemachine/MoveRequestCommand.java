package pasoos.view.gamestatemachine;

import pasoos.hotgammon.Location;

public class MoveRequestCommand implements EventCommand {
    private final Location from;
    private final Location to;
    private boolean result;

    public MoveRequestCommand(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(GammonStateMachine state) {
        result = state.moveRequest(from, to);
    }

    public boolean getResult() {
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + from + ", " + to + ")";
    }

}
