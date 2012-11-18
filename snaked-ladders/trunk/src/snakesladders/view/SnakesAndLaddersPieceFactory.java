package snakesladders.view;

import minidraw.boardgame.BoardFigure;
import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.FigureFactory;
import snakesladders.domain.Game;
import snakesladders.domain.Square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnakesAndLaddersPieceFactory implements FigureFactory<Square> {
    private Game game;

    public SnakesAndLaddersPieceFactory(Game game) {
        this.game = game;
    }

    public Map<Square, List<BoardPiece>> generatePieceMultiMap() {
        Map<Square, List<BoardPiece>> m = new HashMap<Square, List<BoardPiece>>();

        for (int i = 0; i < 35; i++) {
            Square s = new Square(i);
            List<BoardPiece> sl = new ArrayList();
            m.put(s, sl);
        }

        Square square1 = new Square(1);
        BoardFigure redtoken = new BoardFigure("game-token-red", true, new MoveCommand(game));
        List<BoardPiece> square1list = new ArrayList();
        square1list.add(redtoken);
        m.put(square1, square1list);

        Square square20 = new Square(20);
        BoardFigure bluetoken = new BoardFigure("game-token-blue", true, new MoveCommand(game));
        List<BoardPiece> square20list = new ArrayList();
        square20list.add(bluetoken);
        m.put(square20, square20list);

        return m;
    }

    public Map<String, BoardPiece> generatePropMap() {
        BoardPiece die = new BoardFigure("die0", false, new DieRollCommand(game));
        Map<String, BoardPiece> m = new HashMap<String, BoardPiece>();
        m.put(Constant.diePropName, die);
        return m;
    }
}

