package pasoos.hotgammon.gamestatemachine;

public interface GammonState extends GammonStateMachine {
    void onEntry();

    void onExit();

    StateId getStateId();

    void setContext(StateContext context);
}
