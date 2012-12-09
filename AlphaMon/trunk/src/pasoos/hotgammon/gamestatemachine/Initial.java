package pasoos.hotgammon.gamestatemachine;

import pasoos.hotgammon.Location;

public class Initial extends BaseState {

    public Initial(StateId stateId) {
        super(stateId);
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

}