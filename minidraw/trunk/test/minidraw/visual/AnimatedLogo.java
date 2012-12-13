package minidraw.visual;

import minidraw.animation.*;
import minidraw.animation.easings.BezierMovement;
import minidraw.animation.easings.LinearMove;
import minidraw.animation.engine.AnimationEngine;
import minidraw.animation.engine.AnimationEngineImpl;
import minidraw.framework.*;
import minidraw.standard.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class AnimatedLogo implements Tool, AnimationChangeListener {
    private DrawingEditor editor;
    private Figure[][] figures;
    private Image background;
    AnimationEngine ae;
    private Dimension backGroundDimension;
    private Point center;
    private boolean allowAnimationRestart = true;

    public AnimatedLogo(DrawingEditor editor, Figure[][] figures) {
        this.editor = editor;
        editor.open();
        editor.setTool(this);
        this.figures = figures;
        background = ImageManager.getSingleton().getImage("au-logo");
        backGroundDimension = new Dimension(background.getWidth(null), background.getHeight(null));
        center = new Point(backGroundDimension.width / 2, backGroundDimension.height / 2);
        ae = new AnimationEngineImpl(new AnimationTimerImpl());
        for (Figure[] figureRow : figures)
            for (Figure f : figureRow)
                editor.drawing().add(f);
    }

    public static void main(String[] args) {
        Figure[][] figures = new Figure[3][3];
        figures[0][0] = new ImageFigure("11", new Point(5, 5));
        figures[0][1] = new ImageFigure("12", new Point(10, 10));
        figures[0][2] = new ImageFigure("13", new Point(15, 15));
        figures[1][0] = new ImageFigure("21", new Point(20, 20));
        figures[1][1] = new ImageFigure("22", new Point(25, 25));
        figures[1][2] = new ImageFigure("23", new Point(30, 30));
        figures[2][0] = new ImageFigure("31", new Point(35, 35));
        figures[2][1] = new ImageFigure("32", new Point(40, 40));
        figures[2][2] = new ImageFigure("33", new Point(45, 45));

        DrawingEditor editor = new MiniDrawApplication("", new AnimatedLogoFactory());

        new AnimatedLogo(editor, figures);

    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        Drawing model = editor.drawing();
        Figure f = model.findFigure(e.getX(), e.getY());
        if (f != null) {
            allowAnimationRestart = true;
            startAnimation(f);
        } else {
            ae.stopAll();
            allowAnimationRestart = false;
            for (int i = 0; i < figures.length; i++)
                for (int j = 0; j < figures[i].length; j++) {
                    startLogoAnimation(i, j);
                }

        }
    }

    private void startAnimation(Figure f) {
        Animation ba = createBezierAnimation(f, calculateEndpointFromBegin(f.displayBox()));
        ba.addAnimationChangeListener(this);
        ae.startAnimation(ba);
    }

    private MoveAnimation createBezierAnimation(Figure f, Point end) {
        TimeInterval ti = TimeInterval.fromNow().duration(2000);
        return new MoveAnimation(f, end, ti, new BezierMovement(center, center));
    }

    private void startLogoAnimation(int i, int j) {
        Figure f = figures[i][j];

        Point endpoint = new Point(j * backGroundDimension.width / 3, i * backGroundDimension.height / 3);
        Animation ba = createLinearAnimation(f, endpoint);
        ba.addAnimationChangeListener(this);

        ae.startAnimation(ba);
    }

    private Animation createLinearAnimation(Figure f, Point end) {
        TimeInterval ti = TimeInterval.fromNow().duration(2000);
        return new MoveAnimation(f, end, ti, new LinearMove());
    }

    private Point calculateEndpointFromBegin(Rectangle r) {

        if (r.getLocation().x > center.x) {
            if (r.getLocation().y > center.y) {
                //current location is right side and down
                return new Point(0, backGroundDimension.height - r.height);
            } else {
                //current location is right side and down
                return new Point(backGroundDimension.width - r.width, backGroundDimension.height - r.height);
            }
        } else {
            if (r.getLocation().y > center.y) {
                //current location is left side and down
                return new Point(0, 0);
            } else {
                //current location is left side and up
                return new Point(backGroundDimension.width - r.width, 0);
            }
        }
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
    }

    @Override
    public void mouseMove(MouseEvent evt, int x, int y) {
    }

    @Override
    public void keyDown(KeyEvent evt, int key) {
    }

    @Override
    public void onAnimationCompleted(AnimationChangeEvent ace) {
        Figure f = ace.getSource().getFigure();
        if (allowAnimationRestart)
            startAnimation(f);
    }
}

class AnimatedLogoFactory implements Factory {

    public DrawingView createDrawingView(DrawingEditor editor) {
        return new StdViewWithBackground(editor, "au-logo");
    }

    public Drawing createDrawing(DrawingEditor editor) {
        return new StandardDrawing();
    }

    public JTextField createStatusField(DrawingEditor editor) {
        return null;
    }
}

