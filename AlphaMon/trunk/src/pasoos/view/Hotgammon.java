package pasoos.view;

import minidraw.boardgame.BoardActionTool;
import minidraw.boardgame.BoardDrawing;
import minidraw.boardgame.BoardGameObserver;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

public class Hotgammon {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        GameSettings gs = new GameSettingsImpl(args);

        GammonBuilderImpl gammonBuilder = new GammonBuilderImpl();

        GammonBuildDirector director = new GammonBuildDirector(gs, gammonBuilder);
        director.construct();

        DrawingEditor editor = new MiniDrawApplication("Hotgammon game...", gammonBuilder.createViewFactory());

        editor.open();
        editor.setTool(new BoardActionTool(editor));
        gammonBuilder.getStatusMonitor().setEditor(editor);

        Game game = gammonBuilder.getGame();
        BoardGameObserver<Location> gameObserver = (BoardGameObserver<Location>) editor.drawing();
        game.addObserver(new BoardGameObserverAdapter(gameObserver, (BoardDrawing<Location>) editor.drawing()));
    }

}
