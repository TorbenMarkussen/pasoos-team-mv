package pasoos.view.gamestatemachine;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

public class DiceRolledEvent implements EventCommand {
    private int[] values;
    private String stack;

    public DiceRolledEvent(int[] values) {
        this.values = values;
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
        state.diceRolled(values);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + Arrays.toString(values);
    }

}
