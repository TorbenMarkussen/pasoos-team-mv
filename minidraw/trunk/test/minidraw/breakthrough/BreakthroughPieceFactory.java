package minidraw.breakthrough;

import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.Command;
import minidraw.boardgame.FigureFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The factory to generate all pieces.
 * <p/>
 * <p/>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 * <p/>
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com/
 */
public class BreakthroughPieceFactory implements FigureFactory<Position> {

    private Game game;

    public BreakthroughPieceFactory(Game game) {
        super();
        this.game = game;
    }

    public Map<Position, List<BoardPiece>> generatePieceMultiMap() {
        Map<Position, List<BoardPiece>> m = new HashMap<Position, List<BoardPiece>>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int whatIsOnThisSquare = game.get(new Position(row, col));
                List<BoardPiece> l = new ArrayList<BoardPiece>();
                if (whatIsOnThisSquare != Game.NONE) {
                    String gifname = whatIsOnThisSquare == Game.WHITE ?
                            Constants.WHITE_PAWN_IMAGE_NAME :
                            Constants.BLACK_PAWN_IMAGE_NAME;
                    l.add(new BoardFigure(gifname, true, new MoveCommand(game)));
                }
                m.put(new Position(row, col), l);
            }
        }
        return m;
    }

    public Map<String, BoardPiece> generatePropMap() {
        return null;
    }
}

class MoveCommand implements Command {
    private Game game;

    public MoveCommand(Game game) {
        super();
        this.game = game;
    }

    public boolean execute() {
        Position from =
                new Position(
                        (fy - Constants.SQUARE_OFFSET_Y) / Constants.SQUARE_SIZE,
                        (fx - Constants.SQUARE_OFFSET_X) / Constants.SQUARE_SIZE);
        Position to =
                new Position(
                        (ty - Constants.SQUARE_OFFSET_Y) / Constants.SQUARE_SIZE,
                        (tx - Constants.SQUARE_OFFSET_X) / Constants.SQUARE_SIZE);
        // System.out.println( "Move command defined: "+from + " -> "+to);
        if (to.r < 0 || to.r >= 8 || to.c < 0 || to.c >= 8) return false;
        boolean valid = game.move(from, to);
        return valid;
    }

    private int fx, fy, tx, ty;

    public void setFromCoordinates(int fromX, int fromY) {
        fx = fromX;
        fy = fromY;
    }

    public void setToCoordinates(int toX, int toY) {
        tx = toX;
        ty = toY;
    }

}
