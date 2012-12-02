package pasoos.view.gamestatemachine;

public interface EventCommand {
    void execute(State state);
}
