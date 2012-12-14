package pasoos.hotgammon.simplegame;

import minidraw.boardgame.Command;
import pasoos.hotgammon.Game;

public class RollDiceCommand implements Command {
    private Game game;

    public RollDiceCommand(Game game) {
        this.game = game;
    }

    @Override
    public void setFromCoordinates(int fromX, int fromY) {
    }

    @Override
    public void setToCoordinates(int toX, int toY) {
    }

    @Override
    public boolean execute() {
        if (game.getNumberOfMovesLeft() > 0)
            return false;

        game.nextTurn();

        return true;
    }
}
