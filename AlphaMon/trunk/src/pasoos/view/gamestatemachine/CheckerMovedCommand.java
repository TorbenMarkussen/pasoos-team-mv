package pasoos.view.gamestatemachine;

import pasoos.hotgammon.Location;

public class CheckerMovedCommand implements EventCommand {
    private final Location from;
    private Location to;

    public CheckerMovedCommand(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(GammonStateMachine state) {
        state.checkerMoved(from, to);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + from + ", " + to + ")";
    }
}
