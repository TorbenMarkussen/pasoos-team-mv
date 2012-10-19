package pasoos.hotgammon.controller;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gerry.GameAdapter;
import pasoos.hotgammon.gerry.Gerry;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AIPlayerTurnState extends GameControllerStateImpl implements GameAdapter, ActionListener {
    private Gerry aiplayer;
    private Game game;
    private static HashMap<Location, Integer> loc2gerry;
    private static HashMap<Integer, Location> gerry2loc;
    private String status;
    private Timer timer;
    List<ActionListener> actionQueue = new ArrayList<ActionListener>();

    public AIPlayerTurnState(GameContext context, Game g) {
        super(context);
        this.game = g;
        aiplayer = new Gerry();
        timer = new Timer(200, this);
        timer.setInitialDelay(100);
    }

    @Override
    public void entry() {
        actionQueue = new ArrayList<ActionListener>();
        status = "Gerry moved : ";

        aiplayer.play(this);
        addTurnCompletedAction();

        gameContext.updateStatusText(status);
        timer.start();
    }

    private void addTurnCompletedAction() {
        actionQueue.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AIPlayerTurnState.this.turnCompleted();
            }
        });
    }

    private void turnCompleted() {
        timer.stop();
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

        addMoveAction(fl, tl);

        status += fl + " -> " + tl + "; ";
        gameContext.updateStatusText(status);
    }

    private void animateMove(int percentage, Location from, Location to) {
        hgvm.animateMove(from, to, percentage);
    }

    private void commitMove(Location from, Location to) {
        hgvm.move(from, to);
    }

    private void addMoveAction(final Location fl, final Location tl) {
        for (int i = 0; i < 10; i++) {
            actionQueue.add(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AIPlayerTurnState.this.animateMove(10, fl, tl);
                }
            });
        }
        actionQueue.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AIPlayerTurnState.this.commitMove(fl, tl);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (actionQueue.size() > 0)
            actionQueue.remove(0).actionPerformed(e);
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

}
