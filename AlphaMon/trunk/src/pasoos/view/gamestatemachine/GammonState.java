package pasoos.view.gamestatemachine;

public interface GammonState extends GammonStateMachine {
    void onEntry();

    void onExit();

    StateId getStateId();
}
