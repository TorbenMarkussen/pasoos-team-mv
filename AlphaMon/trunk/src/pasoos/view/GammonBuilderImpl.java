package pasoos.view;

import minidraw.boardgame.BoardDrawing;
import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.BoardPiece;
import minidraw.framework.AnimationEngine;
import minidraw.framework.AnimationEngineImpl;
import minidraw.framework.Factory;
import minidraw.standard.AnimationTimerImpl;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameFactory;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.factory.SemiMonFactory;
import pasoos.view.gamestatemachine.GameStateController;
import pasoos.view.gamestatemachine.GammonPlayer;
import pasoos.view.gamestatemachine.ManualPlayerState;
import pasoos.view.gamestatemachine.StateId;

import static pasoos.hotgammon.Color.RED;

public class GammonBuilderImpl implements GammonBuilder {
    private Game game;
    private GammonPlayer redPlayer = null;
    private GammonPlayer blackPlayer = null;
    private GammonDice dice;
    private StatusMonitor statusMonitor;
    private GameStateController gameStateController;
    private HotgammonPieceFactory pieceFactory;
    private BoardDrawing<Location> boardDrawing;
    private AnimationEngine animationEngine = new AnimationEngineImpl(new AnimationTimerImpl());

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
    }

    @Override
    public void setPlayer(Color color, PlayerType playerType, String name) {
        if (color == RED) {
            redPlayer = createPlayer(StateId.RedPlayer, playerType, name);
        } else {
            blackPlayer = createPlayer(StateId.BlackPlayer, playerType, name);
        }
    }

    @Override
    public void addPiece(Location loc, Color color) {
        GammonPlayer player = color == RED ? redPlayer : blackPlayer;
        String figurename = color == RED ? "redchecker" : "blackchecker";

        BoardPiece piece = new BoardFigure(figurename, true, new MoveCommand(gameStateController));
        player.addPiece(piece);

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

    public StatusMonitor getStatusMonitor() {
        return statusMonitor;
    }

    @Override
    public void addDie(String name) {

        BoardPiece p = new BoardFigure("die0", false, dice);
        if (name.equals("die1")) {
            dice.setDie1(p);
        } else if (name.equals("die2")) {
            dice.setDie2(p);
        }
        pieceFactory.addDie(name, p);
    }

    public void build() {


        boardDrawing = new BoardDrawing<Location>(
                pieceFactory.build(),
                new HotgammonPositioningStrategy(),
                dice);

        dice.setAnimationEngine(animationEngine);

        gameStateController.setRedPlayer(redPlayer);
        gameStateController.setBlackPlayer(blackPlayer);
        gameStateController.setGame(game);
        gameStateController.setGammonDice(dice);
        gameStateController.setAnimationEngine(animationEngine);
        gameStateController.setBoardDrawing(boardDrawing);

        gameStateController.addStatusObserver(statusMonitor);
        gameStateController.addStatusObserver(new BoardGameObserverAdapter(boardDrawing));

        game.newGame();
    }

    public Factory createViewFactory() {
        ViewFactoryBuilderImpl builder = new ViewFactoryBuilderImpl();

        builder.setBoardDrawing(boardDrawing);

        return builder.build();
    }

}
