package pasoos.hotgammon.animatedgame.ui;

import minidraw.boardgame.Command;
import pasoos.hotgammon.animatedgame.GammonStateMachine;

public class RollDiceAdapterCommand implements Command {

    private GammonStateMachine gsm;

    public RollDiceAdapterCommand(GammonStateMachine gsm) {
        this.gsm = gsm;
    }

    @Override
    public void setFromCoordinates(int fromX, int fromY) {
    }

    @Override
    public void setToCoordinates(int toX, int toY) {
    }

    @Override
    public boolean execute() {
        gsm.rollDiceRequest();
        return false;
    }
}
