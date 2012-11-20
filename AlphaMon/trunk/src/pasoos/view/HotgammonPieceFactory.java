package pasoos.view;

import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.FigureFactory;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;

public class HotgammonPieceFactory implements FigureFactory<Location> {
    private Game game;
    private final GammonPlayer redPlayer;
    private final GammonPlayer blackPlayer;

    public HotgammonPieceFactory(Game g, GammonPlayer redPlayer, GammonPlayer blackPlayer) {
        game = g;
        this.redPlayer = redPlayer;
        this.blackPlayer = blackPlayer;
    }

    @Override
    public Map<Location, List<BoardPiece>> generatePieceMultiMap() {
        Map<Location, List<BoardPiece>> map = new HashMap<Location, List<BoardPiece>>();
        for (Location loc : Location.values()) {
            List<BoardPiece> l = createList(game.getColor(loc), game.getCount(loc));
            map.put(loc, l);
        }
        return map;
    }

    private List<BoardPiece> createList(Color color, int count) {
        List<BoardPiece> l = new ArrayList<BoardPiece>();
        for (int i = 0; i < count; i++) {
            if (color == BLACK) {
                l.add(createBlackBoardPiece());
            } else if (color == RED) {
                l.add(createRedBoardPiece());
            }
        }
        return l;
    }

    protected BoardFigure createRedBoardPiece() {
        return new BoardFigure("redchecker", true, redPlayer);
    }

    protected BoardFigure createBlackBoardPiece() {
        return new BoardFigure("blackchecker", true, blackPlayer);
    }

    @Override
    public Map<String, BoardPiece> generatePropMap() {
        Map<String, BoardPiece> m = new HashMap<String, BoardPiece>();
        m.put("die1", new BoardFigure("die0", false, new DieRollCommand(game)));
        m.put("die2", new BoardFigure("die0", false, new DieRollCommand(game)));
        return m;
    }
}
