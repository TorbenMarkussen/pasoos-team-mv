package pasoos.hotgammon.animatedgame.gamestatemachine;

import pasoos.hotgammon.animatedgame.GammonStateMachine;

public interface GammonState extends GammonStateMachine {
    void onEntry();

    void onExit();

    StateId getStateId();

    void setContext(StateContext context);
}
