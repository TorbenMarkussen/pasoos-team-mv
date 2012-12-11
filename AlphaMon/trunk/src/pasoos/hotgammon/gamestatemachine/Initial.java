package pasoos.hotgammon.gamestatemachine;

import minidraw.boardgame.NullAnimationCallback;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.obsolete.minidraw_controller.GammonMove;

public class Initial extends BaseState {
    private int animationCount;

    public Initial(StateId stateId) {
        super(stateId);
    }

    @Override
    public void blackPlayerActive() {
        System.out.println("blackPlayerActive");
        context.setState(StateId.BlackPlayer);
    }

    @Override
    public void redPlayerActive() {
        System.out.println("redPlayerActive");
        context.setState(StateId.RedPlayer);
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return false;
    }

    @Override
    public void rackGame() {
        System.out.println("StartGameRackAnimation");
        animateRack();
    }

    private void animateRack() {
        Game game = context.getGame();

        for (Location loc : Location.values()) {
            int count = game.getCount(loc);
            Color color = game.getColor(loc);

            for (int i = 0; i < count; i++) {
                Location from = color == Color.BLACK ? Location.B_BEAR_OFF : Location.R_BEAR_OFF;
                startAnimation(new GammonMove(from, loc));
            }
        }
    }

    private void startAnimation(final GammonMove move) {
        animationCount++;
        context.moveAnimated(
                move.getFrom(),
                move.getTo(),
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
            context.rollDice();
        }
    }

}