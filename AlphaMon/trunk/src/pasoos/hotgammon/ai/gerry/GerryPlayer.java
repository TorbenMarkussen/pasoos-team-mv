package pasoos.hotgammon.ai.gerry;

import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.ai.AIPlayer;
import pasoos.hotgammon.minidraw_controller.GammonMove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerryPlayer implements AIPlayer, GameAdapter {
    private Game game;
    private Gerry gerry;
    private List<GammonMove> gerryMoves;

    public GerryPlayer(Game game) {
        this.game = game;
        gerry = new Gerry();
    }

    @Override
    public void play() {
        gerryMoves = new ArrayList<GammonMove>();
        gerry.play(this);
    }

    @Override
    public List<GammonMove> getMoves() {
        return gerryMoves;
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
        return game.diceThrown();
    }

    @Override
    public void move(int from, int to) {
        Location fl = gerry2loc.get(from);
        Location tl = gerry2loc.get(to);
        gerryMoves.add(new GammonMove(fl, tl));
    }

    private static HashMap<Location, Integer> loc2gerry;
    private static HashMap<Integer, Location> gerry2loc;

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
