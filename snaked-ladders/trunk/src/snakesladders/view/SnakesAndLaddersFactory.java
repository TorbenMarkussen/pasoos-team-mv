package snakesladders.view;

import minidraw.boardgame.BoardDrawing;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.StdViewWithBackground;
import snakesladders.domain.Game;
import snakesladders.domain.Square;

import javax.swing.*;

public class SnakesAndLaddersFactory implements Factory {
    private Game game;

    public SnakesAndLaddersFactory(Game game) {
        this.game = game;
    }

    public DrawingView createDrawingView(DrawingEditor editor) {
        DrawingView view = new StdViewWithBackground(editor, "snakes-and-ladders-background");
        return view;
    }

    public Drawing createDrawing(DrawingEditor editor) {
        return new BoardDrawing<Square>(new SnakesAndLaddersPieceFactory(game),
                new SnakesAndLaddersPositioningStrategy(),
                new SnakesAndLaddersPropAppearanceStrategy(game));
    }

    public JTextField createStatusField(DrawingEditor editor) {
        JTextField statusField = new JTextField("Show BoardFigures...");
        statusField.setEditable(false);
        return statusField;
    }
}
