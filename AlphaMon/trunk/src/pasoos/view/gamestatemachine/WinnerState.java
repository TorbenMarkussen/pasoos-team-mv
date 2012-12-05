package pasoos.view.gamestatemachine;

import pasoos.hotgammon.Location;
import pasoos.view.BaseState;

public class WinnerState extends BaseState {

    public WinnerState(StateId stateId) {
        super(stateId);
    }

    @Override
    public void onEntry() {
        context.getSoundMachine().playVictorySound();
        context.updateStatus(this, "Winner is " + context.getWinnerName());
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return false;
    }

}
