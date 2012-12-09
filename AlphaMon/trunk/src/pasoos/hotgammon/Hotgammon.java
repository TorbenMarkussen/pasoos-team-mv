package pasoos.hotgammon;

import minidraw.boardgame.BoardActionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import pasoos.hotgammon.settings.GameSettings;
import pasoos.hotgammon.settings.GameSettingsImpl;
import pasoos.hotgammon.ui.GammonBuildDirector;
import pasoos.hotgammon.ui.GammonBuilderImpl;

public class Hotgammon {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        GameSettings gameSettings = new GameSettingsImpl(args);

        GammonBuilderImpl gammonBuilder = new GammonBuilderImpl();

        GammonBuildDirector director = new GammonBuildDirector(gameSettings, gammonBuilder);
        director.construct();
        gammonBuilder.build();

        DrawingEditor editor = new MiniDrawApplication("Hotgammon game...", gammonBuilder.createViewFactory());

        editor.open();
        editor.setTool(new BoardActionTool(editor));
        gammonBuilder.getStatusMonitor().setEditor(editor);

        gammonBuilder.getGame().newGame();
    }

}
