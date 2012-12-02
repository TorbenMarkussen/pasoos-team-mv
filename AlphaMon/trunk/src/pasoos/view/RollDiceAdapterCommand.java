package pasoos.view;

import minidraw.boardgame.Command;

public class RollDiceAdapterCommand implements Command {

    private GammonDice dice;

    public RollDiceAdapterCommand(GammonDice dice) {
        this.dice = dice;
    }

    @Override
    public void setFromCoordinates(int fromX, int fromY) {
    }

    @Override
    public void setToCoordinates(int toX, int toY) {
    }

    @Override
    public boolean execute() {
        dice.rollRequest();
        return false;
    }
}
