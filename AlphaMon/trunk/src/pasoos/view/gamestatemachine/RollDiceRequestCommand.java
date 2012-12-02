package pasoos.view.gamestatemachine;

public class RollDiceRequestCommand implements EventCommand {
    @Override
    public void execute(GammonStateMachine state) {
        state.rollDiceRequest();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
