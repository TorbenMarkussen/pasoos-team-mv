package pasoos.hotgammon.obsolete.minidraw_controller;

import pasoos.hotgammon.Color;

import java.awt.event.MouseEvent;

public class NextTurnGameState extends GameControllerStateImpl {

    public NextTurnGameState(GameContext context) {
        super(context);
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int i, int i1) {
        hgvm.nextTurn();
        gameContext.setState(gameContext.getTurnState(hgvm.getPlayerInTurn()));
    }

    @Override
    public void entry() {
        if (hgvm.winner() != Color.NONE) {
            gameContext.setState(new WinnerFoundState(gameContext));
        } else {
            gameContext.updateStatusText("click for next turn");
        }

    }
}
