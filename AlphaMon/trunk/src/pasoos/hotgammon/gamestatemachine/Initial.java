package pasoos.hotgammon.gamestatemachine;

import minidraw.boardgame.NullAnimationCallback;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

public class Initial extends BaseState {
    private int animationCount;

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

    @Override
    public void initiateGame() {
        System.out.println("StartSetupCheckersAnimation");
        animateCheckerLineUp();
    }

    private void animateCheckerLineUp() {
        Game game = context.getGame();

        for (Location loc : Location.values()) {
            int count = game.getCount(loc);
            Color color = game.getColor(loc);

            for (int i = 0; i < count; i++) {
                Location from = color == Color.BLACK ? Location.B_BEAR_OFF : Location.R_BEAR_OFF;
                startAnimation(from, loc);
            }
        }
    }

    private void startAnimation(Location from, Location to) {
        animationCount++;
        context.moveAnimated(
                from,
                to,
                new NullAnimationCallback<Location>() {
                    @Override
                    public void afterEnd(Location from, Location to) {
                        animationEnded();
                    }
                });
    }

    private void animationEnded() {
        animationCount--;
        if (animationCount == 0) {
            context.getGame().newGame();
            context.getCurrentState().blackPlayerActive();
        }
    }

}