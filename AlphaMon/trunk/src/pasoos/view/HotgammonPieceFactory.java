package pasoos.view;

import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.FigureFactory;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotgammonPieceFactory implements FigureFactory<Location>, PieceFactoryBuilder {
    private Map<Location, List<BoardPiece>> locationPieceListMap;
    private Map<String, BoardPiece> diceMap;

    public HotgammonPieceFactory() {
        locationPieceListMap = new HashMap<Location, List<BoardPiece>>();
        for (Location loc : Location.values()) {
            locationPieceListMap.put(loc, new ArrayList<BoardPiece>());
        }
        diceMap = new HashMap<String, BoardPiece>();
    }

    @Override
    public Map<Location, List<BoardPiece>> generatePieceMultiMap() {
        return locationPieceListMap;
    }

    @Override
    public Map<String, BoardPiece> generatePropMap() {
        return diceMap;
    }

    @Override
    public void addPiece(Location loc, Color color, BoardPiece piece) {
        locationPieceListMap.get(loc).add(piece);
    }

    @Override
    public void addDie(String dieName, BoardPiece piece) {
        diceMap.put(dieName, piece);
    }

    @Override
    public FigureFactory<Location> build() {
        return this;
    }
}
