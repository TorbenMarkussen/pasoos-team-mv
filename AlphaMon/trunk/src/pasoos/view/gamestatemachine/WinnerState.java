package pasoos.view.gamestatemachine;

import pasoos.hotgammon.Location;
import pasoos.view.NullState;

public class WinnerState extends NullState {
    private StateId stateId;
    private StateContext context;

    public WinnerState(StateId stateId, StateContext context) {
        this.stateId = stateId;
        this.context = context;
    }

    @Override
    public void onEntry() {
        context.getSoundMachine().playVictorySound();
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return false;
    }

    @Override
    public StateId getStateId() {
        return stateId;
    }
}
