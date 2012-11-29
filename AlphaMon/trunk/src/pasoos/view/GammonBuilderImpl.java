package pasoos.view;

import minidraw.boardgame.BoardDrawing;
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
    private GammonPlayer redPlayer = null;
    private GammonPlayer blackPlayer = null;
    private GammonDice dice;
    private StatusMonitor statusMonitor;
    private GameStateController gameStateController;
    private HotgammonPieceFactory pieceFactory;
    private BoardDrawing<Location> boardDrawing;

    public GammonBuilderImpl() {
        gameStateController = new GameStateController();
        statusMonitor = new StatusMonitor();
        pieceFactory = new HotgammonPieceFactory();
    }

    @Override
    public void setGameType(Class<? extends HotGammonFactory> gameFactoryType) {
        Game g;
        try {
            g = GameFactory.createGame(gameFactoryType);
        } catch (IllegalAccessException e) {
            g = GameFactory.createGame(new SemiMonFactory());
        } catch (InstantiationException e) {
            g = GameFactory.createGame(new SemiMonFactory());
        }
        game = new GameEventDecorator(g, gameStateController);
        dice = new GammonDice(game, gameStateController);

        gameStateController.setGame(game);
        gameStateController.addStatusObserver(statusMonitor);
    }

    @Override
    public void setPlayer(Color color, PlayerType playerType, String name) {
        if (color == Color.RED) {
            redPlayer = createPlayer(StateId.RedPlayer, playerType, name);
            gameStateController.setRedPlayer(redPlayer);
        } else {
            blackPlayer = createPlayer(StateId.BlackPlayer, playerType, name);
            gameStateController.setBlackPlayer(blackPlayer);
        }

        if (redPlayer != null && blackPlayer != null)
            game.newGame();
    }

    @Override
    public void addPiece(Location loc, Color color) {
        BoardPiece piece = null;

        if (color == Color.RED) {
            piece = new BoardFigure("redchecker", true, new MoveCommand(gameStateController));
            redPlayer.addPiece(piece);
        } else {
            piece = new BoardFigure("blackchecker", true, new MoveCommand(gameStateController));
            blackPlayer.addPiece(piece);
        }

        pieceFactory.addPiece(loc, color, piece);
    }

    private GammonPlayer createPlayer(StateId stateId, PlayerType playerType, String name) {
        if (playerType == PlayerType.ManualPlayer)
            return new ManualPlayerState(stateId, gameStateController, name);

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

    @Override
    public void addDie(String name) {
        HotgammonPropAppearanceStrategy propAppearanceStrategy = new HotgammonPropAppearanceStrategy(getGame());
        String imgName = propAppearanceStrategy.calculateImageNameForPropWithKey(name);

        BoardPiece p = new BoardFigure(imgName, false, dice);
        if (name.equals("die1")) {
            dice.addDie1(p);
        } else if (name.equals("die2")) {
            dice.addDie2(p);
        }
        pieceFactory.addDie(name, p);
    }

    public void build() {
        boardDrawing = new BoardDrawing<Location>(
                pieceFactory.build(),
                new HotgammonPositioningStrategy(),
                new HotgammonPropAppearanceStrategy(getGame()));

        gameStateController.setBoardDrawing(boardDrawing);
    }

    public Factory createViewFactory() {
        ViewFactoryBuilderImpl builder = new ViewFactoryBuilderImpl();

        builder.setBoardDrawing(boardDrawing);

        return builder.build();
    }

}
