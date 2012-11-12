package pasoos.hotgammon.minidraw_controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameController extends GameControllerStateImpl {

    public GameController(GameContext context) {
        super(context);
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent, int x, int y) {
        gameContext.getCurrentState().mouseDown(mouseEvent, x, y);
    }

    @Override
    public void mouseDrag(MouseEvent mouseEvent, int x, int y) {
        gameContext.getCurrentState().mouseDrag(mouseEvent, x, y);
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int x, int y) {
        gameContext.getCurrentState().mouseUp(mouseEvent, x, y);
    }

    @Override
    public void mouseMove(MouseEvent mouseEvent, int x, int y) {
        gameContext.getCurrentState().mouseMove(mouseEvent, x, y);
    }

    @Override
    public void keyDown(KeyEvent keyEvent, int i) {
        gameContext.getCurrentState().keyDown(keyEvent, i);
    }
}
