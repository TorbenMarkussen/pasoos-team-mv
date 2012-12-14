package pasoos.hotgammon.simplegame;

import minidraw.boardgame.BoardActionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import pasoos.hotgammon.builder.GammonBuildDirector;
import pasoos.hotgammon.settings.GameSettings;
import pasoos.hotgammon.settings.GameSettingsImpl;

public class SimpleGammon {
    public static void main(String[] args) {
        GameSettings gameSettings = new GameSettingsImpl(args);

        SimpleGammonBuilder gammonBuilder = new SimpleGammonBuilder();

        GammonBuildDirector director = new GammonBuildDirector(gameSettings, gammonBuilder);
        director.construct();
        gammonBuilder.build();

        DrawingEditor editor = new MiniDrawApplication("Hotgammon game...", gammonBuilder.createViewFactory());

        editor.open();
        editor.setTool(new BoardActionTool(editor));
        gammonBuilder.getGame().newGame();
    }
}
