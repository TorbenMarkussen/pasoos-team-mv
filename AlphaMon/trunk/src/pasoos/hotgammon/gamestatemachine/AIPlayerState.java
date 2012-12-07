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

    public AIPlayerState(StateId stateId, String name, AIPlayer aiPlayer) {
        super(stateId);
        this.name = name;
        this.aiPlayer = aiPlayer;
        allowRoll = false;
    }

    @Override
    public void onEntry() {
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
    public boolean moveRequest(Location from, Location to) {
        return false;
    }

    @Override
    public void diceRolled(int[] values) {
        try {
            aiPlayer.play();
            moves = aiPlayer.getMoves();

            System.out.print("moving:");
            for (GammonMove m : moves) {
                System.out.print(m + ", ");
            }
            System.out.println();

            processGerryMoves();
            context.notifyDiceRolled(values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkerMoved(final Location from, final Location to) {
        if (to == Location.R_BAR || to == Location.B_BAR) {
            System.out.println(name + " strikes " + from + " -> " + to);
            writeStatus(name + " strikes " + from + " -> " + to);
            context.getBoard().moveAnimated(
                    from,
                    to,
                    new NullAnimationCallback<Location>() {
                        @Override
                        public void beforeEnd(Location from, Location to) {
                            context.getSoundMachine().playCheckerMoveSound();
                        }
                    });
        }
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
        context.getBoard().moveAnimated(
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
                    }
                }
        );
    }

}
