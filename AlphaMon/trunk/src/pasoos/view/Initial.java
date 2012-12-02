package pasoos.view;

import pasoos.hotgammon.Location;
import pasoos.view.gamestatemachine.StateContext;
import pasoos.view.gamestatemachine.StateId;

public class Initial extends NullState {
    private StateId stateId;
    private StateContext context;

    public Initial(StateId stateId, StateContext context) {
        this.stateId = stateId;
        this.context = context;
    }

    @Override
    public void blackPlayerActive() {
        context.setState(StateId.BlackPlayer);
    }

    @Override
    public void redPlayerActive() {
        context.setState(StateId.RedPlayer);
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
