package pasoos.view;

import minidraw.boardgame.BoardActionTool;
import minidraw.boardgame.BoardDrawing;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gameengine.GameImpl;
import pasoos.hotgammon.rules.factory.SemiMonFactory;

public class Hotgammon {
    public static void main(String[] args) {
        Game game = new GameImpl(new SemiMonFactory());
        game.newGame();

        DrawingEditor editor = new MiniDrawApplication("Hotgammon game...", new HotgammonViewFactory(game));
        editor.open();
        editor.setTool(new BoardActionTool(editor));
        BoardDrawing<Location> drawing = (BoardDrawing<Location>) editor.drawing();
        game.addObserver(new BoardGameObserverAdapter(drawing));
    }

}
