package pasoos.view.gamestatemachine;

public class TurnEndedCommand implements EventCommand {
    @Override
    public void execute(State state) {
        state.turnEnded();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
