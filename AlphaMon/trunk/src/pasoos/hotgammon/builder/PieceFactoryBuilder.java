package pasoos.hotgammon.builder;

import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.FigureFactory;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;

public interface PieceFactoryBuilder {
    void addPiece(Location loc, Color color, BoardPiece piece);

    void addDie(String dieName, BoardPiece piece);

    FigureFactory<Location> build();
}
