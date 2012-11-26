package pasoos.view;

import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.BoardPiece;
import minidraw.framework.Factory;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameFactory;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.factory.SemiMonFactory;

public class GammonBuilderImpl implements GammonBuilder {
    private Game game;
    private GammonPlayer redPlayer;
    private GammonPlayer blackPlayer;
    private GammonDice dice;
    private StatusMonitor statusMonitor;
    private GameStateController gameStateController;
    private HotgammonPieceFactory pieceFactory;

    public GammonBuilderImpl() {
        gameStateController = new GameStateController();
        statusMonitor = new StatusMonitor();
        pieceFactory = new HotgammonPieceFactory();
    }

    @Override
    public void setGameType(Class<? extends HotGammonFactory> gameFactoryType) {
        try {
            game = GameFactory.createGame(gameFactoryType);
        } catch (IllegalAccessException e) {
            game = GameFactory.createGame(new SemiMonFactory());
        } catch (InstantiationException e) {
            game = GameFactory.createGame(new SemiMonFactory());
        }
        game.newGame();

        dice = new GammonDice(game);

        gameStateController.setGame(game);
    }

    @Override
    public void setPlayer(Color color, PlayerType playerType, String name) {
        if (color == Color.RED) {
            redPlayer = createPlayer(playerType, name);
            redPlayer.addStatusObserver(statusMonitor);
            gameStateController.setRedPlayer(redPlayer);
        } else {
            blackPlayer = createPlayer(playerType, name);
            blackPlayer.addStatusObserver(statusMonitor);
            gameStateController.setBlackPlayer(blackPlayer);
        }

    }

    @Override
    public void addPiece(Location loc, Color color) {
        BoardPiece piece = null;

        if (color == Color.RED) {
            piece = new BoardFigure("redchecker", true, redPlayer);
            redPlayer.addPiece(piece);
        } else {
            piece = new BoardFigure("blackchecker", true, blackPlayer);
            blackPlayer.addPiece(piece);
        }

        pieceFactory.addPiece(loc, color, piece);
    }

    private GammonPlayer createPlayer(PlayerType playerType, String name) {
        if (playerType == PlayerType.ManualPlayer)
            return new ManualPlayer(game, name);

        return null;
    }

    public Game getGame() {
        return game;
    }

    public GammonPlayer getBlackPlayer() {
        return blackPlayer;
    }

    public GammonPlayer getRedPlayer() {
        return redPlayer;
    }

    public GameStateController getGameStateController() {
        return gameStateController;
    }

    public GammonDice getDice() {
        return dice;
    }

    public StatusMonitor getStatusMonitor() {
        return statusMonitor;
    }

    public BoardPiece addDie(String name) {
        BoardPiece p = null;
        if (name.equals("die1")) {
            p = new BoardFigure("die0", false, dice);
            dice.addDie1(p);
            pieceFactory.addDie(name, p);
        } else if (name.equals("die2")) {
            p = new BoardFigure("die0", false, dice);
            dice.addDie2(p);
            pieceFactory.addDie(name, p);
        }

        return p;
    }

    public Factory createViewFactory() {
        ViewFactoryBuilderImpl builder = new ViewFactoryBuilderImpl();

        builder.setPieceFactory(pieceFactory.build()).
                setPositioningStrategy(new HotgammonPositioningStrategy()).
                setPropAppearanceStrategy(new HotgammonPropAppearanceStrategy(getGame()));

        return builder.build();
    }
}
