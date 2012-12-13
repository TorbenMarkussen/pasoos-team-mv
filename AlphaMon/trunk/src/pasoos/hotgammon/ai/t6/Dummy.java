package pasoos.hotgammon.ai.t6;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.ai.AIPlayer;
import pasoos.hotgammon.ai.GammonMove;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.gameengine.InitializableBoard;
import pasoos.hotgammon.rules.BoardInitializerStrategy;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.validator.BackgammonMoveValidatorStrategy;

import java.util.*;

import static pasoos.hotgammon.Location.*;

public class Dummy implements AIPlayer, BoardInitializerStrategy {

    private Game game;
    private Color playerColor;
    private List<GammonMove> moves;
    private MoveValidatorStrategy moveValidator;
    private Board board;


    public Dummy(Game game, Color playerColor) {
        this.game = game;
        this.playerColor = playerColor;

    }

    @Override
    public void play() {
        moves = new ArrayList<GammonMove>();
        board = new BoardImpl(this);
        moveValidator = new BackgammonMoveValidatorStrategy(board);

        List<Integer> diceValuesLeft = new ArrayList<Integer>();
        for (int d : game.diceValuesLeft()) {
            diceValuesLeft.add(d);
        }

        while (diceValuesLeft.size() > 0) {
            List<Move> moveCandidates = findMoveCandidates(diceValuesLeft);
            if (moveCandidates.size() > 0) {
                Collections.sort(moveCandidates, new SimpleSort());
                Move move = moveCandidates.get(0);
                moves.add(new GammonMove(move.from, move.to));
                board.decrementLocation(move.from, playerColor);
                if (board.getColor(move.to) == playerColor.getOpponentColor())
                    board.decrementLocation(move.to, playerColor.getOpponentColor());
                board.incrementLocation(move.to, playerColor);

                int index = diceValuesLeft.indexOf(move.die);
                diceValuesLeft.remove(index);
            } else {
                diceValuesLeft.clear();
            }
        }
    }

    private List<Move> findMoveCandidates(List<Integer> dice) {
        List<Location> locationSequence = locationOrder.get(playerColor);
        List<Move> moveCandidates = new ArrayList<Move>();
        for (Integer d : dice) {
            for (Location l : locationSequence) {
                Location destination = Location.findLocation(playerColor, l, d);
                if (moveValidator.isValidMove(l, destination, d)) {
                    Move m = new Move();
                    m.from = l;
                    m.to = destination;
                    m.die = d;
                    moveCandidates.add(m);
                }
            }
        }
        return moveCandidates;
    }

    @Override
    public List<GammonMove> getMoves() {
        return moves;
    }

    @Override
    public void initialize(InitializableBoard board) {
        for (Location l : Location.values()) {
            board.set(l, game.getColor(l), game.getCount(l));
        }
    }


    static Map<Color, List<Location>> locationOrder = new HashMap<Color, List<Location>>();

    static {
        List<Location> redOrder = new ArrayList<Location>();
        redOrder.add(R_BAR);
        redOrder.add(B1);
        redOrder.add(B2);
        redOrder.add(B3);
        redOrder.add(B4);
        redOrder.add(B5);
        redOrder.add(B6);
        redOrder.add(B7);
        redOrder.add(B8);
        redOrder.add(B9);
        redOrder.add(B10);
        redOrder.add(B11);
        redOrder.add(B12);
        redOrder.add(R12);
        redOrder.add(R11);
        redOrder.add(R10);
        redOrder.add(R9);
        redOrder.add(R8);
        redOrder.add(R7);
        redOrder.add(R6);
        redOrder.add(R5);
        redOrder.add(R4);
        redOrder.add(R3);
        redOrder.add(R2);
        redOrder.add(R1);
        locationOrder.put(Color.RED, redOrder);
        List<Location> blackOrder = new ArrayList<Location>();
        blackOrder.add(B_BAR);
        blackOrder.add(R1);
        blackOrder.add(R2);
        blackOrder.add(R3);
        blackOrder.add(R4);
        blackOrder.add(R5);
        blackOrder.add(R6);
        blackOrder.add(R7);
        blackOrder.add(R8);
        blackOrder.add(R9);
        blackOrder.add(R10);
        blackOrder.add(R11);
        blackOrder.add(R12);
        blackOrder.add(B12);
        blackOrder.add(B11);
        blackOrder.add(B10);
        blackOrder.add(B9);
        blackOrder.add(B8);
        blackOrder.add(B7);
        blackOrder.add(B6);
        blackOrder.add(B5);
        blackOrder.add(B4);
        blackOrder.add(B3);
        blackOrder.add(B2);
        blackOrder.add(B1);
        locationOrder.put(Color.BLACK, blackOrder);
    }

    private class Move {
        Location from;
        Location to;
        int die;
    }

    private class SimpleSort implements Comparator<Move> {
        @Override
        public int compare(Move o1, Move o2) {
            if (o1.from == Location.getBar(playerColor))
                return 1;
            return 0;
        }
    }
}



