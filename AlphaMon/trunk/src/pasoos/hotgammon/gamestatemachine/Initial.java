package pasoos.hotgammon.gamestatemachine;

import minidraw.boardgame.NullAnimationCallback;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.obsolete.minidraw_controller.GammonMove;

import java.util.ArrayList;
import java.util.List;

public class Initial extends BaseState {
    private List<GammonMove> moves;
    private List<GammonMove> processedMoves;

    public Initial(StateId stateId) {
        super(stateId);
        moves = new ArrayList<GammonMove>();
        processedMoves = new ArrayList<GammonMove>();
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

    private void processRackMoves() {
        if (moves.size() > 0) {
            GammonMove move = moves.remove(0);
            startAnimation(move);
            processedMoves.add(move);
        }
    }

    private void animateRack() {
        Game game = context.getGame();
        for (Location loc : Location.values()) {
            int count = game.getCount(loc);
            Color color = game.getColor(loc);

            for (int i = 0; i < count; i++) {
                if (color == Color.BLACK)
                    moves.add(new GammonMove(Location.B_BEAR_OFF, loc));
                else
                    moves.add(new GammonMove(Location.R_BEAR_OFF, loc));
            }
        }
        processRackMoves();
    }

    private void startAnimation(final GammonMove move) {
        context.moveAnimated(
                move.getFrom(),
                move.getTo(),
                new NullAnimationCallback<Location>() {
                    @Override
                    public void afterStart(Location from, Location to) {
                        processRackMoves();
                    }

                    @Override
                    public void afterEnd(Location from, Location to) {
                        startGameAfterRack();
                    }
                });
    }

    private void startGameAfterRack() {
        processedMoves.remove(0);
        if (processedMoves.size() == 0) {
            context.getGame().newGame();
            context.rollDice();
        }
    }

}