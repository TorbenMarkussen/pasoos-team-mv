package pasoos.view;

import minidraw.boardgame.BoardActionTool;
import minidraw.boardgame.BoardDrawing;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameFactory;
import pasoos.hotgammon.Location;

public class Hotgammon {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        GameSettings gs = new GameSettingsImpl(args);
        Game game = GameFactory.createGame(gs.getGameFactoryType());

        GammonPlayer blackPlayer = new ManualPlayer(game, "black");
        GammonPlayer redPlayer = new ManualPlayer(game, "red");
        StatusMonitor sm = new StatusMonitor();
        blackPlayer.addStatusObserver(sm);
        redPlayer.addStatusObserver(sm);
        GameStateController gameStateController = new GameStateController(game, redPlayer, blackPlayer);

        game.newGame();

        DrawingEditor editor = new MiniDrawApplication("Hotgammon game...", new HotgammonViewFactory(game, redPlayer, blackPlayer));

        editor.open();
        editor.setTool(new BoardActionTool(editor));
        sm.setEditor(editor);

        BoardDrawing<Location> drawing = (BoardDrawing<Location>) editor.drawing();
        game.addObserver(new BoardGameObserverAdapter(drawing));
    }

}
