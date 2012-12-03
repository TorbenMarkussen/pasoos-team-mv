package pasoos.hotgammon.minidraw_controller;

import minidraw.framework.*;
import minidraw.standard.AnimationTimerImpl;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gerry.GameAdapter;
import pasoos.hotgammon.gerry.Gerry;
import pasoos.hotgammon.minidraw_view.Checker;
import pasoos.physics.Convert;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AIPlayerTurnState extends GameControllerStateImpl implements GameAdapter, AnimationChangeListener {
    private Gerry aiplayer;
    private Game game;
    private static HashMap<Location, Integer> loc2gerry;
    private static HashMap<Integer, Location> gerry2loc;
    private String status;
    private List<GammonMove> gerryMoves;
    AnimationEngine aengine = new AnimationEngineImpl(new AnimationTimerImpl());
    private GammonMove animatingMove;

    public AIPlayerTurnState(GameContext context, Game g) {
        super(context);
        this.game = g;
        aiplayer = new Gerry();
        gerryMoves = new ArrayList<GammonMove>();
    }

    @Override
    public void entry() {
        status = "Gerry moved : ";

        gerryMoves = new ArrayList<GammonMove>();
        aiplayer.play(this);

        startAnimation(gerryMoves.remove(0));

        gameContext.updateStatusText(status);
    }

    private void startAnimation(GammonMove gm) {
        Point start = Convert.locationAndCount2xy(gm.getFrom(), hgvm.getCount(gm.getFrom()) - 1);
        Point dest = Convert.locationAndCount2xy(gm.getTo(), hgvm.getCount(gm.getTo()));
        Point bezPointA = new Point(start.x, 220);
        Point bezPointB = new Point(dest.x, 220);
        Checker checker = hgvm.getTopChecker(gm.getFrom());

        animatingMove = gm;
        Animation ba = new MoveAnimation(checker, dest, TimeInterval.fromNow().duration(1000), new BezierMovement(bezPointA, bezPointB));
        ba.addAnimationChangeListener(this);

        aengine.startAnimation(ba);
    }

    private void movedChecker(GammonMove gm) {
        hgvm.move(gm.getFrom(), gm.getTo());

        if (gerryMoves.size() > 0) {
            startAnimation(gerryMoves.remove(0));
        } else {
            turnCompleted();
        }
    }

    private void turnCompleted() {
        if (hgvm.winner() == Color.NONE) {
            gameContext.setState(new NextTurnGameState(gameContext));
        } else {
            gameContext.setState(new WinnerFoundState(gameContext));
        }
    }

    @Override
    public int[] getBoard() {
        int[] board = new int[28];
        for (Location l : Location.values()) {
            board[loc2gerry.get(l)] = game.getCount(l) * game.getColor(l).getSign();
        }
        return board;

    }

    @Override
    public int[] getDices() {
        return game.diceValuesLeft();
    }

    @Override
    public void move(int from, int to) {
        Location fl = gerry2loc.get(from);
        Location tl = gerry2loc.get(to);
        gerryMoves.add(new GammonMove(fl, tl));
    }

    static {
        loc2gerry = new HashMap<Location, Integer>();
        loc2gerry.put(Location.B1, 24);
        loc2gerry.put(Location.B2, 23);
        loc2gerry.put(Location.B3, 22);
        loc2gerry.put(Location.B4, 21);
        loc2gerry.put(Location.B5, 20);
        loc2gerry.put(Location.B6, 19);
        loc2gerry.put(Location.B7, 18);
        loc2gerry.put(Location.B8, 17);
        loc2gerry.put(Location.B9, 16);
        loc2gerry.put(Location.B10, 15);
        loc2gerry.put(Location.B11, 14);
        loc2gerry.put(Location.B12, 13);

        loc2gerry.put(Location.R1, 1);
        loc2gerry.put(Location.R2, 2);
        loc2gerry.put(Location.R3, 3);
        loc2gerry.put(Location.R4, 4);
        loc2gerry.put(Location.R5, 5);
        loc2gerry.put(Location.R6, 6);
        loc2gerry.put(Location.R7, 7);
        loc2gerry.put(Location.R8, 8);
        loc2gerry.put(Location.R9, 9);
        loc2gerry.put(Location.R10, 10);
        loc2gerry.put(Location.R11, 11);
        loc2gerry.put(Location.R12, 12);

        // the bars
        loc2gerry.put(Location.B_BAR, 0);
        loc2gerry.put(Location.R_BAR, 25);

        // the bearing off locations
        loc2gerry.put(Location.B_BEAR_OFF, 26);
        loc2gerry.put(Location.R_BEAR_OFF, 27);

        gerry2loc = new HashMap<Integer, Location>();
        for (Map.Entry<Location, Integer> kvp : loc2gerry.entrySet()) {
            gerry2loc.put(kvp.getValue(), kvp.getKey());
        }
    }

    @Override
    public void OnAnimationStarted(AnimationChangeEvent ace) {

    }

    @Override
    public void onAnimationCompleted(AnimationChangeEvent ace) {
        movedChecker(animatingMove);
    }
}
