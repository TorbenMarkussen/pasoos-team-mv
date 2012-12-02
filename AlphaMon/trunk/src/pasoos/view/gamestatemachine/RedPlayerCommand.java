package pasoos.view.gamestatemachine;

public class RedPlayerCommand implements EventCommand {
    @Override
    public void execute(GammonStateMachine state) {
        state.redPlayerActive();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
