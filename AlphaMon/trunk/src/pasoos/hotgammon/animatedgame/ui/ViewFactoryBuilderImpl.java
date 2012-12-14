package pasoos.hotgammon.animatedgame.ui;

import minidraw.boardgame.BoardGameDrawing;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.StdViewWithBackground;
import pasoos.hotgammon.Location;

import javax.swing.*;

public class ViewFactoryBuilderImpl implements ViewFactoryBuilder, Factory {
    private BoardGameDrawing<Location> boardDrawing;

    @Override
    public DrawingView createDrawingView(DrawingEditor editor) {
        return new StdViewWithBackground(editor, "board");
    }

    @Override
    public Drawing createDrawing(DrawingEditor editor) {
        return boardDrawing;
    }

    @Override
    public JTextField createStatusField(DrawingEditor editor) {
        JTextField statusField = new JTextField("Show BoardFigures...");
        statusField.setEditable(false);
        return statusField;
    }

    @Override
    public void setBoardDrawing(BoardGameDrawing<Location> boardDrawing) {
        this.boardDrawing = boardDrawing;
    }

    @Override
    public Factory build() {
        return this;
    }
}