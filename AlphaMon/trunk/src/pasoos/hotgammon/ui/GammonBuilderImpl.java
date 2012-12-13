package pasoos.hotgammon.ui;

import minidraw.boardgame.*;
import minidraw.framework.AnimationEngine;
import minidraw.framework.AnimationEngineImpl;
import minidraw.framework.Factory;
import pasoos.hotgammon.ai.t6.Dummy;
import pasoos.hotgammon.sounds.SoundResource;
import minidraw.standard.AnimationTimerImpl;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameFactory;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.ai.gerry.GerryPlayer;
import pasoos.hotgammon.gamestatemachine.*;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.factory.SemiMonFactory;
import pasoos.hotgammon.settings.PlayerType;
import pasoos.hotgammon.ui.status.StatusMonitor;

import static pasoos.hotgammon.Color.RED;

public class GammonBuilderImpl implements GammonBuilder {
    private Game game;
    private GammonPlayer redPlayer = null;
    private GammonPlayer blackPlayer = null;
    private GammonDice dice;
    private StatusMonitor statusMonitor;
    private GameStateController gameStateController;
    private HotgammonPieceFactory pieceFactory;
    private AnimatedBoard<Location> boardDrawing;
    private AnimationEngine animationEngine = new AnimationEngineImpl(new AnimationTimerImpl());
    private SoundResource soundEngine = new SoundResource(true);

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
        String figurename = color == RED ? "redchecker" : "blackchecker";

        BoardPiece piece = new BoardFigure(figurename, true, new MoveCommand(gameStateController));

        pieceFactory.addPiece(loc, color, piece);
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
        BoardPiece p = new BoardFigure("idie", false, new RollDiceAdapterCommand(dice));
        if (name.equals("die1")) {
            dice.setDie1(p);
        } else if (name.equals("die2")) {
            dice.setDie2(p);
        }
        pieceFactory.addDie(name, p);
    }

    public void build() {

        AnimatedBoardDrawingFactory animationFactory = new HotgammonAnimatedBoardDrawingFactory(pieceFactory.build(), dice, animationEngine);
        boardDrawing = new AnimatedBoardDrawing<Location>(animationFactory);

        dice.setAnimationEngine(animationEngine);
        dice.setSoundEngine(soundEngine);
        StateContext context = new StateContextImpl.Builder(redPlayer, blackPlayer, game, boardDrawing, dice).build();
        context.addStatusObserver(statusMonitor);
        context.addStatusObserver(new BoardGameObserverAdapter(boardDrawing));
        gameStateController.setContext(context);
    }

    public Factory createViewFactory() {
        ViewFactoryBuilderImpl builder = new ViewFactoryBuilderImpl();

        builder.setBoardDrawing(boardDrawing);

        return builder.build();
    }

    public GammonStateMachine getController() {
        return gameStateController;
    }
}
