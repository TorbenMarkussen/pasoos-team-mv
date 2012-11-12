package pasoos.view;

import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.FigureFactory;
import minidraw.boardgame.NullCommand;
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

    public HotgammonPieceFactory(Game g) {
        game = g;
    }

    @Override
    public Map<Location, List<BoardFigure>> generatePieceMultiMap() {
        Map<Location, List<BoardFigure>> map = new HashMap<Location, List<BoardFigure>>();
        for (Location loc : Location.values()) {
            List<BoardFigure> l = createList(game.getColor(loc), game.getCount(loc));
            map.put(loc, l);
        }
        return map;
    }

    private List<BoardFigure> createList(Color color, int count) {
        List<BoardFigure> l = new ArrayList<BoardFigure>();
        for (int i = 0; i < count; i++) {
            if (color == BLACK) {
                l.add(createBoardFigure("blackchecker"));
            } else if (color == RED) {
                l.add(createBoardFigure("redchecker"));
            }

        }
        return l;
    }

    protected BoardFigure createBoardFigure(String imgname) {
        return new BoardFigure(imgname, true, new NullCommand());
    }

    @Override
    public Map<String, BoardFigure> generatePropMap() {
        Map<String, BoardFigure> m = new HashMap<String, BoardFigure>();
        m.put("die1", new BoardFigure("die0", false, new NullCommand()));
        m.put("die2", new BoardFigure("die0", false, new NullCommand()));
        return m;
    }
}
