package pasoos.view.gamestatemachine;

public class TurnEndedCommand implements EventCommand {
    @Override
    public void execute(GammonStateMachine state) {
        state.turnEnded();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
