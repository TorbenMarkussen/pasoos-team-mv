package pasoos.view.gamestatemachine;

import pasoos.hotgammon.Location;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CheckerMovedCommand implements EventCommand {
    private final Location from;
    private Location to;
    private String stack;

    public CheckerMovedCommand(Location from, Location to) {
        this.from = from;
        this.to = to;
        captureStackTrace();
    }

    private void captureStackTrace() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException re) {
            StringWriter sw = new StringWriter();
            re.printStackTrace(new PrintWriter(sw));
            stack = sw.toString();
        }
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
