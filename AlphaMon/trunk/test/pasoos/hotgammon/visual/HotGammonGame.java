package pasoos.hotgammon.visual;

import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.StandardDrawing;
import minidraw.standard.StdViewWithBackground;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.controller.*;
import pasoos.hotgammon.gameengine.GameImpl;
import pasoos.hotgammon.rules.factory.SemiMonFactory;
import pasoos.hotgammon.view.HotGammonViewModel;

import javax.swing.*;

public class HotGammonGame {

    public static void main(String[] args) {
        DrawingEditor editor = new MiniDrawApplication("Show HotGammon figures...", new HotGammonViewFactory());
        editor.open();

        Game g = new GameImpl(new SemiMonFactory());
        g.newGame();

        HotGammonViewModel hgvm = new HotGammonViewModel(g, editor.drawing());

        GameContext context = new GameContext(editor, hgvm);
        context.setBlackPlayerTurnState(new AIPlayerTurnState(context, g));
        context.setRedPlayerTurnState(new ManualPlayerTurnState(context));
        context.setState(new NextTurnGameState(context));

        editor.setTool(new GameController(context));

    }
}


class HotGammonViewFactory implements Factory {
    public DrawingView createDrawingView(DrawingEditor editor) {
        return new StdViewWithBackground(editor, "board");
    }

    public Drawing createDrawing(DrawingEditor editor) {
        return new StandardDrawing();
    }

    public JTextField createStatusField(DrawingEditor editor) {
        JTextField statusField = new JTextField("Hello HotGammon...");
        statusField.setEditable(false);
        return statusField;
    }
}


