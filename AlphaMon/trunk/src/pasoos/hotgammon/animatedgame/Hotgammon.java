package pasoos.hotgammon.animatedgame;

import minidraw.boardgame.BoardActionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import pasoos.hotgammon.builder.GammonBuildDirector;
import pasoos.hotgammon.settings.GameSettings;
import pasoos.hotgammon.settings.GameSettingsImpl;

public class Hotgammon {
    public static void main(String[] args) {

        GameSettings gameSettings = new GameSettingsImpl(args);

        GammonBuilderImpl gammonBuilder = new GammonBuilderImpl();

        GammonBuildDirector director = new GammonBuildDirector(gameSettings, gammonBuilder);
        director.construct();
        gammonBuilder.build();

        DrawingEditor editor = new MiniDrawApplication("Hotgammon game...", gammonBuilder.createViewFactory());

        editor.open();
        editor.setTool(new BoardActionTool(editor));
        gammonBuilder.getStatusMonitor().setEditor(editor);

        gammonBuilder.getController().rackGame();
    }

}
