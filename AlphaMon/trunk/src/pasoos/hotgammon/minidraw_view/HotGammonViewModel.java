package pasoos.hotgammon.minidraw_view;

import minidraw.framework.Drawing;
import minidraw.framework.Figure;
import minidraw.standard.ImageFigure;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;
import pasoos.physics.Convert;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;

public class HotGammonViewModel implements GameObserver {

    private EnumMap<Location, List<Checker>> board;

    List<Figure> dices = new ArrayList<Figure>();
    List<Figure> blackCheckers = new ArrayList<Figure>();
    List<Figure> redCheckers = new ArrayList<Figure>();

    private Game game;
    private Drawing drawing;
    int checkerCount = 0;


    public HotGammonViewModel(Game game, Drawing drawing) {
        this.game = game;
        game.addObserver(this);
        this.drawing = drawing;

        board = new EnumMap<Location, List<Checker>>(Location.class);
        for (Location l : Location.values())
            board.put(l, new ArrayList<Checker>());

        addDices(game);
        addCheckers(game);
    }

    private void addDices(Game game) {
        dicesChanged(game.diceThrown());
    }

    private void addCheckers(Game game) {
        for (Location l : Location.values()) {
            pasoos.hotgammon.Color c = game.getColor(l);
            if (c == BLACK) {
                addBlackCheckers(l, game.getCount(l));
            } else if (c == RED) {
                addRedCheckers(l, game.getCount(l));
            }
        }
    }

    private void addDie(ImageFigure dieFigure) {
        dices.add(dieFigure);
        drawing.add(dieFigure);
    }

    private void addBlackCheckers(Location l, int count) {
        for (int i = 0; i < count; i++) {
            Checker checker = addChecker(BLACK, l);
            blackCheckers.add(checker);
        }
    }

    private Checker addChecker(Color color, Location location) {
        List<Checker> locationCheckers = board.get(location);
        Point p = Convert.locationAndCount2xy(location, locationCheckers.size());
        Checker checker = createChecker(color, p);
        locationCheckers.add(checker);
        drawing.add(checker);
        return checker;
    }

    private Checker createChecker(Color color, Point p) {
        return new Checker(color, p, checkerCount++);
    }

    private void addRedCheckers(Location l, int count) {
        for (int i = 0; i < count; i++) {
            Checker checker = addChecker(RED, l);
            redCheckers.add(checker);
        }
    }

    public void dicesChanged(int[] dices) {
        clearDices();
        for (int i = 0; i < dices.length; i++) {
            int die = dices[i];
            addDie(new ImageFigure("die" + die, new Point(216 + i * 90, 202)));
        }
    }

    private void clearDices() {
        for (Figure f : dices) {
            drawing.remove(f);
        }
    }

    public void nextTurn() {
        game.nextTurn();
    }

    public Color getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    public boolean move(Location moveFrom, Location moveTo) {
        boolean moved = game.move(moveFrom, moveTo);
        return moved;
    }

    public Checker getChecker(int x, int y) {
        Figure f = drawing.findFigure(x, y);
        if (f != null) {
            if (blackCheckers.contains(f))
                return (Checker) f;
            if (redCheckers.contains(f))
                return (Checker) f;
        }
        return null;
    }

    public boolean isMoveable(int x, int y) {
        Checker c = getChecker(x, y);
        if (c == null)
            return false;
        if (c.getColor() != game.getPlayerInTurn())
            return false;

        Location l = Convert.xy2Location(x, y);
        List<Checker> lcs = board.get(l);
        if (c.equals(lcs.get(lcs.size() - 1)))
            return true;

        return false;
    }

    public void restorePostion(Checker checker, Location from) {
        Point p = Convert.locationAndCount2xy(from, game.getCount(from) - 1);
        checker.setPosition(p);
    }

    public int[] diceValuesLeft() {
        return game.diceValuesLeft();
    }

    public Color winner() {
        return game.winner();
    }

    @Override
    public void checkerMove(Location from, Location to) {
        List<Checker> f = board.get(from);
        List<Checker> t = board.get(to);
        Checker c = f.remove(f.size() - 1);
        t.add(c);
        c.setPosition(Convert.locationAndCount2xy(to, t.size() - 1));
    }

    @Override
    public void diceRolled(int[] values) {
        dicesChanged(values);
    }

    public Checker getTopChecker(Location location) {
        List<Checker> f = board.get(location);
        return f.get(f.size() - 1);
    }

    public int getCount(Location location) {
        return board.get(location).size();
    }
}
