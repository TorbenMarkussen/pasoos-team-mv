package pasoos.view.gamestatemachine;

public interface EventCommand {
    void execute(GammonStateMachine state);
}
