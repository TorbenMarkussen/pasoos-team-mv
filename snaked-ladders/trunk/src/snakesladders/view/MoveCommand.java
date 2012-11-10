package snakesladders.view;

import minidraw.boardgame.Command;
import snakesladders.domain.Game;
import snakesladders.domain.Square;

public class MoveCommand implements Command {
    private Game game;

    public MoveCommand(Game game) {
        this.game = game;
    }

    public boolean execute() {
        int from = indexFromXY(fx, fy);
        int to = indexFromXY(tx, ty);
        Square f = new Square(from), t = new Square(to);
        boolean value = game.move(0, new Square(from), new Square(to));
        return value;
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

    private int indexFromXY(int x, int y) {
        int sqc = (x - 17) / 92, sqr = (472 - y) / 92;
        int squareindex = 1 + sqr * 7 + sqc;
        if (squareindex > 17)
            squareindex--; // jump the missing center square
        return squareindex;
    }
}