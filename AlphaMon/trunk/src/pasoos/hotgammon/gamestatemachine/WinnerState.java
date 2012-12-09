package pasoos.hotgammon.gamestatemachine;

import pasoos.hotgammon.Location;

public class WinnerState extends BaseState {

    public WinnerState(StateId stateId) {
        super(stateId);
    }

    @Override
    public void onEntry() {
        context.getSoundMachine().playVictorySound();
        context.updateStatus(this, "Winner is player " + context.getWinnerName());
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return false;
    }
}
