package pasoos.hotgammon.minidraw_controller;

import minidraw.framework.DrawingEditor;
import pasoos.hotgammon.minidraw_view.HotGammonViewModel;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class GameControllerStateImpl implements GameControllerState {
    protected GameContext gameContext;
    protected DrawingEditor editor;
    protected HotGammonViewModel hgvm;


    public GameControllerStateImpl(GameContext context) {
        setGameContext(context);
    }

    @Override
    public void setGameContext(GameContext context) {
        this.gameContext = context;
        editor = gameContext.getEditor();
        hgvm = gameContext.getViewModel();
    }

    @Override
    public void entry() {
    }

    @Override
    public void exit() {
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent, int x, int y) {
    }

    @Override
    public void mouseDrag(MouseEvent mouseEvent, int x, int y) {
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int x, int y) {
    }

    @Override
    public void mouseMove(MouseEvent mouseEvent, int x, int y) {
    }

    @Override
    public void keyDown(KeyEvent keyEvent, int i) {
    }
}
