package pasoos.hotgammon.animatedgame;

import minidraw.animatedboard.AnimatedBoard;
import minidraw.animatedboard.AnimatedBoardDrawing;
import minidraw.animatedboard.AnimatedBoardDrawingFactory;
import minidraw.animation.engine.AnimationEngine;
import minidraw.animation.engine.AnimationEngineImpl;
import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.BoardPiece;
import minidraw.framework.Factory;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameFactory;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.ai.gerry.GerryPlayer;
import pasoos.hotgammon.ai.t6.Dummy;
import pasoos.hotgammon.animatedgame.gamestatemachine.*;
import pasoos.hotgammon.animatedgame.ui.*;
import pasoos.hotgammon.animatedgame.ui.status.StatusMonitor;
import pasoos.hotgammon.builder.GammonBuilder;
import pasoos.hotgammon.builder.PieceFactoryBuilder;
import pasoos.hotgammon.builder.ViewFactoryBuilder;
import pasoos.hotgammon.builder.minidrawfactories.HotgammonPieceFactory;
import pasoos.hotgammon.builder.minidrawfactories.ViewFactoryBuilderImpl;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.factory.SemiMonFactory;
import pasoos.hotgammon.settings.PlayerType;
import pasoos.hotgammon.sounds.SoundResource;

import java.util.HashMap;
import java.util.Map;

import static pasoos.hotgammon.Color.RED;

public class GammonBuilderImpl implements GammonBuilder {
    private Game game;
    private GammonPlayer redPlayer = null;
    private GammonPlayer blackPlayer = null;
    private StatusMonitor statusMonitor;
    private GameStateController gameStateController;
    private PieceFactoryBuilder pieceFactory;
    private AnimatedBoard<Location> boardDrawing;
    private AnimationEngine animationEngine = new AnimationEngineImpl.Builder().build();
    private SoundResource soundEngine = new SoundResource(true);
    private Map<String, BoardPiece> diceMap = new HashMap<String, BoardPiece>();

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
        String figurename = color == RED ? "redchecker" : "blackchecker";

        BoardPiece piece = createChecker(figurename, gameStateController);

        pieceFactory.addPiece(Location.getBearOff(color), color, piece);
    }

    private GammonPlayer createPlayer(StateId stateId, PlayerType playerType, String name) {
        if (playerType == PlayerType.ManualPlayer)
            return new ManualPlayerState(stateId, name);
        else if (playerType == PlayerType.AIPlayer)
            return new AIPlayerState(stateId, name, new GerryPlayer(game));
        else if (playerType == PlayerType.DummyPlayer)
            return new AIPlayerState(stateId, name, new Dummy(game, Color.RED));

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
        BoardPiece p = createDie(gameStateController);
        diceMap.put(name, p);
        pieceFactory.addDie(name, p);
    }

    public void build() {

        GammonDice dice = new GammonDice(game, animationEngine, soundEngine, diceMap);

        AnimatedBoardDrawingFactory animationFactory =
                new HotgammonAnimatedBoardDrawingFactory(pieceFactory.build(), dice, animationEngine);

        boardDrawing = new AnimatedBoardDrawing<Location>(animationFactory);

        StateContext context = new StateContextImpl.Builder(
                redPlayer,
                blackPlayer,
                game,
                boardDrawing,
                dice,
                gameStateController).build();
        context.addStatusObserver(statusMonitor);
        context.addStatusObserver(new BoardGameObserverAdapter(boardDrawing));
    }

    public Factory createViewFactory() {
        ViewFactoryBuilder builder = new ViewFactoryBuilderImpl();

        builder.setBoardDrawing(boardDrawing);

        return builder.build();
    }

    public GammonStateMachine getController() {
        return gameStateController;
    }

    protected BoardPiece createChecker(String figurename, GammonStateMachine gsc) {
        return new BoardFigure(figurename, true, new MoveCommand(gsc));
    }

    protected BoardPiece createDie(GammonStateMachine gsc) {
        return new BoardFigure("idie", false, new RollDiceAdapterCommand(gsc));
    }
}
