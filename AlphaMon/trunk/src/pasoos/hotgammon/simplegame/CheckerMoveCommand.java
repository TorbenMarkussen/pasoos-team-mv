package pasoos.hotgammon.simplegame;

import minidraw.boardgame.Command;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.boardphysics.Convert;

public class CheckerMoveCommand implements Command {
    private Game game;
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    public CheckerMoveCommand(Game game) {
        this.game = game;
    }

    @Override
    public void setFromCoordinates(int fromX, int fromY) {
        this.fromX = fromX;
        this.fromY = fromY;
    }

    @Override
    public void setToCoordinates(int toX, int toY) {
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public boolean execute() {
        Location from = Convert.xy2Location(fromX, fromY);
        Location to = Convert.xy2Location(toX, toY);
        return game.move(from, to);
    }
}
