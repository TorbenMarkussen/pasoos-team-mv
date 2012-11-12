package pasoos.view;

import minidraw.boardgame.BoardDrawing;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.StdViewWithBackground;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

import javax.swing.*;

public class HotgammonViewFactory implements Factory {
    private Game game;

    public HotgammonViewFactory(Game game) {
        this.game = game;
    }

    @Override
    public DrawingView createDrawingView(DrawingEditor editor) {
        DrawingView view = new StdViewWithBackground(editor, "board");
        return view;
    }

    @Override
    public Drawing createDrawing(DrawingEditor editor) {
        return new BoardDrawing<Location>(new HotgammonPieceFactory(game),
                new HotgammonPositioningStrategy(),
                new HotgammonPropAppearanceStrategy(game));

    }

    @Override
    public JTextField createStatusField(DrawingEditor editor) {
        JTextField statusField = new JTextField("Show BoardFigures...");
        statusField.setEditable(false);
        return statusField;
    }
}
