package pasoos.hotgammon.animatedgame.gamestatemachine;

import minidraw.animatedboard.NullMoveAnimationCallback;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.ai.AIPlayer;
import pasoos.hotgammon.ai.GammonMove;

import java.util.List;


/**
 * Responsible for
 */
public class AIPlayerState extends BaseState implements GammonPlayer {
    private final String name;
    private AIPlayer aiPlayer;
    private List<GammonMove> moves;
    private int activeAnimationCount;
    private boolean turnEnded;
    private PlayerState aiplayerState;

    public AIPlayerState(StateId stateId, String name, AIPlayer aiPlayer) {
        super(stateId);
        this.name = name;
        this.aiPlayer = aiPlayer;
        activeAnimationCount = 0;
    }

    @Override
    public void onEntry() {
        turnEnded = false;
        activeAnimationCount = 0;
        System.out.println("entry:" + name);
        writeStatus(name + " in turn");
        aiplayerState = PlayerState.Rolling;
        context.rollDice();
    }

    private void writeStatus(String s) {
        context.updateStatus(this, s);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void turnEnded() {
        turnEnded = true;
        if (aiplayerState == PlayerState.Done) {
            context.playerTurnEnded(this);
        }
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return false;
    }

    @Override
    public void diceRolled(int[] values) {
        aiplayerState = PlayerState.Moving;
        context.notifyDiceRolled(values);

        aiPlayer.play();
        moves = aiPlayer.getMoves();
        if (moves.size() > 0)
            processGerryMoves();
        else
            aiplayerState = PlayerState.Done;
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
                new NullMoveAnimationCallback<Location>() {
                    @Override
                    public void afterMoveEnd(Location from, Location to) {
                        context.getSoundMachine().playCheckerMoveSound();
                        decreaseActiveAnimationCount();
                    }
                });
    }

    private void decreaseActiveAnimationCount() {
        activeAnimationCount--;
        if (activeAnimationCount == 0) {
            aiplayerState = PlayerState.Done;
            if (turnEnded)
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

    private void startAnimatedMove(GammonMove move) {
        System.out.println("starting move " + move);
        writeStatus(name + " moves " + move);
        increaseActiveAnimationCount();
        context.moveAnimated(
                move.getFrom(),
                move.getTo(),
                new NullMoveAnimationCallback<Location>() {
                    @Override
                    public void beforeMoveEnd(Location from, Location to) {
                        context.getGame().move(from, to);
                        context.getSoundMachine().playCheckerMoveSound();
                    }

                    @Override
                    public void afterMoveEnd(Location from, Location to) {
                        processGerryMoves();
                        decreaseActiveAnimationCount();
                    }
                }
        );
    }

    enum PlayerState {
        Moving, Done, Rolling

    }
}
