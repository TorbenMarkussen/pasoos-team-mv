package pasoos.view;

import pasoos.hotgammon.Location;

public class CheckerMovedCommand implements EventCommand {
    private final State state;
    private final Location from;
    private Location to;

    public CheckerMovedCommand(State state, Location from, Location to) {
        this.state = state;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        state.checkerMoved(from, to);
    }
}
