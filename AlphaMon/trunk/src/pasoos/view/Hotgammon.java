package pasoos.view;

import minidraw.boardgame.BoardActionTool;
import minidraw.boardgame.BoardDrawing;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameFactory;
import pasoos.hotgammon.Location;

public class Hotgammon {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        GameSettings gs = new GameSettingsImpl(args);

        Game game = GameFactory.createGame(gs.getGameFactoryType());

        game.newGame();

        DrawingEditor editor = new MiniDrawApplication("Hotgammon game...", new HotgammonViewFactory(game));
        editor.open();
        editor.setTool(new BoardActionTool(editor));
        BoardDrawing<Location> drawing = (BoardDrawing<Location>) editor.drawing();
        game.addObserver(new BoardGameObserverAdapter(drawing));
    }

}
