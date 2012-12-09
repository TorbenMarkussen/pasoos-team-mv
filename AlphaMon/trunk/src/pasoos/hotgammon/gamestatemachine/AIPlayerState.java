package pasoos.hotgammon.gamestatemachine;

import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.NullAnimationCallback;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.ai.AIPlayer;
import pasoos.hotgammon.obsolete.minidraw_controller.GammonMove;

import java.util.List;

public class AIPlayerState extends BaseState implements GammonPlayer {
    private final String name;
    private AIPlayer aiPlayer;
    private List<GammonMove> moves;
    private static boolean allowRoll;
    private int activeAnimationCount;
    private boolean turnEnded;

    public AIPlayerState(StateId stateId, String name, AIPlayer aiPlayer) {
        super(stateId);
        this.name = name;
        this.aiPlayer = aiPlayer;
        allowRoll = false;
        activeAnimationCount = 0;
    }

    @Override
    public void onEntry() {
        turnEnded = false;
        activeAnimationCount = 0;
        System.out.println("entry:" + name);
        writeStatus(name + " in turn");
        if (allowRoll) {
            context.rollDice();
        }
        allowRoll = true;
    }

    private void writeStatus(String s) {
        context.updateStatus(this, s);
    }

    @Override
    public void addPiece(BoardPiece piece) {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void turnEnded() {
        turnEnded = true;
        if (activeAnimationCount == 0) {
            context.playerTurnEnded(this);
        }
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return false;
    }

    @Override
    public void diceRolled(int[] values) {
        try {

            aiPlayer.play();
            moves = aiPlayer.getMoves();
            processGerryMoves();
            context.notifyDiceRolled(values);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkerMoved(final Location from, final Location to) {
        if (isCheckerMovedToBar(to)) {
            startCheckerToBarMove(from, to);
        }
    }

    private void startCheckerToBarMove(Location from, Location to) {
        System.out.println(name + " strikes " + from + " -> " + to);
        writeStatus(name + " strikes " + from + " -> " + to);
        increaseActiveAnimationCount();
        context.moveAnimated(
                from,
                to,
                new NullAnimationCallback<Location>() {
                    @Override
                    public void afterEnd(Location from, Location to) {
                        context.getSoundMachine().playCheckerMoveSound();
                        decreaseActiveAnimationCount();
                    }
                });
    }

    private void decreaseActiveAnimationCount() {
        activeAnimationCount--;
        if (turnEnded && activeAnimationCount == 0) {
            context.playerTurnEnded(this);
        }
    }

    private void increaseActiveAnimationCount() {
        activeAnimationCount++;
    }

    private boolean isCheckerMovedToBar(Location to) {
        return to == Location.R_BAR || to == Location.B_BAR;
    }

    private void processGerryMoves() {
        if (moves.size() > 0) {
            GammonMove move = moves.remove(0);
            startAnimatedMove(move);
        }
    }

    private void startAnimatedMove(final GammonMove move) {
        System.out.println("starting move " + move);
        writeStatus(name + " moves " + move);
        increaseActiveAnimationCount();
        context.moveAnimated(
                move.getFrom(),
                move.getTo(),
                new NullAnimationCallback<Location>() {
                    @Override
                    public void beforeEnd(Location from, Location to) {
                        context.getGame().move(from, move.getTo());
                        context.getSoundMachine().playCheckerMoveSound();
                    }

                    @Override
                    public void afterEnd(Location from, Location to) {
                        processGerryMoves();
                        decreaseActiveAnimationCount();
                    }
                }
        );
    }

}
