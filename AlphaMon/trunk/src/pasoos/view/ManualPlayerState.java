package pasoos.view;

import pasoos.hotgammon.Location;

public class ManualPlayerState extends NullState {
    private GammonPlayer player;
    private StateContext context;
    private boolean allowRoll;

    public ManualPlayerState(GammonPlayer player, StateContext context) {
        player.setState(this);
        this.player = player;
        this.context = context;
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return context.getGame().move(from, to);
    }

    @Override
    public void rollDiceRequest() {
        if ((context.getGame().getNumberOfMovesLeft() == 0) && allowRoll)
            context.getGame().nextTurn();
    }

    @Override
    public void onEntry() {
        allowRoll = true;
        player.setActive();
    }

    @Override
    public void diceRolled(int[] values) {
        allowRoll = false;
    }

    @Override
    public void turnEnded() {
        allowRoll = false;
        player.setInactive();
    }

    @Override
    public void blackPlayerActive() {
        context.setState(GammonState.BlackPlayer);
    }

    @Override
    public void redPlayerActive() {
        context.setState(GammonState.RedPlayer);
    }
}
