package pasoos.view.gamestatemachine;

public class BlackPlayerCommand implements EventCommand {
    @Override
    public void execute(GammonStateMachine state) {
        state.blackPlayerActive();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
