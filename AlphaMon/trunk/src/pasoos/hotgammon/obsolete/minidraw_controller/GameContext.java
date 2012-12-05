package pasoos.hotgammon.obsolete.minidraw_controller;

import minidraw.framework.DrawingEditor;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.obsolete.minidraw_view.HotGammonViewModel;

public class GameContext {
    private DrawingEditor editor;
    private HotGammonViewModel viewModel;
    private GameControllerState currentState;
    private GameControllerState blackPlayerTurnState;
    private GameControllerState redPlayerTurnState;

    public GameContext(DrawingEditor editor, HotGammonViewModel viewModel) {
        this.editor = editor;
        this.viewModel = viewModel;
        currentState = null;
    }

    public DrawingEditor getEditor() {
        return editor;
    }

    public HotGammonViewModel getViewModel() {
        return viewModel;
    }

    public void setState(GameControllerState state) {
        GameControllerState previousState = currentState;
        currentState = state;

        if (previousState != currentState) {
            if (previousState != null)
                previousState.exit();
            currentState.entry();
        }
    }

    public GameControllerState getCurrentState() {
        return currentState;
    }

    public void updateStatusText(String s) {
        editor.showStatus(s);
    }

    public GameControllerState getTurnState(Color playerInTurn) {
        if (playerInTurn == Color.BLACK) {
            return blackPlayerTurnState;
        } else
            return redPlayerTurnState;

    }

    public void setBlackPlayerTurnState(GameControllerState blackPlayerTurnState) {
        this.blackPlayerTurnState = blackPlayerTurnState;
    }

    public void setRedPlayerTurnState(GameControllerState redPlayerTurnState) {
        this.redPlayerTurnState = redPlayerTurnState;
    }
}
