package pasoos.view.gamestatemachine;

public class RedPlayerCommand implements EventCommand {
    @Override
    public void execute(State state) {
        state.redPlayerActive();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
