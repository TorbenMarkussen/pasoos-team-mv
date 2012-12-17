package pasoos.hotgammon.simplegame;

import minidraw.boardgame.BoardDrawing;
import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.BoardPiece;
import minidraw.framework.Factory;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.ai.gerry.GerryPlayer;
import pasoos.hotgammon.ai.t6.Dummy;
import pasoos.hotgammon.builder.GammonBuilder;
import pasoos.hotgammon.builder.PieceFactoryBuilder;
import pasoos.hotgammon.builder.ViewFactoryBuilder;
import pasoos.hotgammon.builder.minidrawfactories.HotgammonPieceFactory;
import pasoos.hotgammon.builder.minidrawfactories.ViewFactoryBuilderImpl;
import pasoos.hotgammon.gameengine.GameImpl;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.factory.SemiMonFactory;
import pasoos.hotgammon.settings.PlayerType;

import static pasoos.hotgammon.Color.RED;

public class SimpleGammonBuilder implements GammonBuilder {

    private Game game = null;
    PieceFactoryBuilder pieceFactory = new HotgammonPieceFactory();
    private BoardDrawing<Location> boardDrawing;

    @Override
    public void setGameType(Class<? extends HotGammonFactory> gameFactoryType) {
        try {
            game = new GameImpl(gameFactoryType.newInstance());
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }

        if (game == null) {
            game = new GameImpl(new SemiMonFactory());
        }
    }

    @Override
    public void setPlayer(Color color, PlayerType playerType, String name) {
        if (playerType == PlayerType.AIPlayer) {
            Player aiplayer = new Player(game, new GerryPlayer(game), color);
            game.addObserver(aiplayer);
        }
        if (playerType == PlayerType.DummyPlayer) {
            Player aiplayer = new Player(game, new Dummy(game, color), color);
            game.addObserver(aiplayer);
        }

    }

    @Override
    public void addPiece(Location loc, Color color) {
        String figurename = color == RED ? "redchecker" : "blackchecker";
        BoardPiece piece = new BoardFigure(figurename, true, new CheckerMoveCommand(game));
        pieceFactory.addPiece(loc, color, piece);
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void addDie(String name) {
        BoardPiece p = new BoardFigure("idie", false, new RollDiceCommand(game));
        pieceFactory.addDie(name, p);
    }

    public void build() {
        boardDrawing = new BoardDrawing<Location>(
                pieceFactory.build(),
                new CheckerPositioningStrategy(),
                new DieAppearance(game));

        game.addObserver(new BoardObserverAdapter(boardDrawing));

    }

    public Factory createViewFactory() {
        ViewFactoryBuilder builder = new ViewFactoryBuilderImpl();

        builder.setBoardDrawing(boardDrawing);

        return builder.build();

    }
}
