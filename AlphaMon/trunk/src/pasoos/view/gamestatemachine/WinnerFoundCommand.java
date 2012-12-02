package pasoos.view.gamestatemachine;

public class WinnerFoundCommand implements EventCommand {
    @Override
    public void execute(State state) {
        state.winnerFound();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
