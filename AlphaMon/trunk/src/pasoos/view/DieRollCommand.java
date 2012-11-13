package pasoos.view;

import minidraw.boardgame.Command;
import pasoos.hotgammon.Game;

public class DieRollCommand implements Command {

    private Game game;

    DieRollCommand(Game game) {
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
        if (isTurnCompleted()) {
            game.nextTurn();
        }
        return true;
    }

    private boolean isTurnCompleted() {
        return game.diceValuesLeft().length == 0;
    }
}
