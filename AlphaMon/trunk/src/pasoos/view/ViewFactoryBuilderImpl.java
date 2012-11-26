package pasoos.view;

import minidraw.boardgame.BoardDrawing;
import minidraw.boardgame.FigureFactory;
import minidraw.boardgame.PositioningStrategy;
import minidraw.boardgame.PropAppearanceStrategy;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.StdViewWithBackground;
import pasoos.hotgammon.Location;

import javax.swing.*;

public class ViewFactoryBuilderImpl implements ViewFactoryBuilder, Factory {
    private FigureFactory<Location> pieceFactory;
    private PositioningStrategy<Location> positioningStrategy;
    private PropAppearanceStrategy appearanceStrategy;

    @Override
    public DrawingView createDrawingView(DrawingEditor editor) {
        return new StdViewWithBackground(editor, "board");
    }

    @Override
    public Drawing createDrawing(DrawingEditor editor) {
        return new BoardDrawing<Location>(
                pieceFactory,
                positioningStrategy,
                appearanceStrategy);
    }

    @Override
    public JTextField createStatusField(DrawingEditor editor) {
        JTextField statusField = new JTextField("Show BoardFigures...");
        statusField.setEditable(false);
        return statusField;
    }

    @Override
    public ViewFactoryBuilder setPieceFactory(FigureFactory<Location> pieceFactory) {
        this.pieceFactory = pieceFactory;
        return this;
    }

    @Override
    public ViewFactoryBuilder setPositioningStrategy(PositioningStrategy<Location> positioningStrategy) {
        this.positioningStrategy = positioningStrategy;
        return this;
    }

    @Override
    public ViewFactoryBuilder setPropAppearanceStrategy(PropAppearanceStrategy appearanceStrategy) {
        this.appearanceStrategy = appearanceStrategy;
        return this;
    }

    @Override
    public Factory build() {
        return this;
    }
}
