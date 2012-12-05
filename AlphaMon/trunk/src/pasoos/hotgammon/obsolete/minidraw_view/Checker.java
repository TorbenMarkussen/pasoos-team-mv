package pasoos.hotgammon.obsolete.minidraw_view;

import minidraw.standard.ImageFigure;
import pasoos.hotgammon.Color;

import java.awt.*;

public class Checker extends ImageFigure {
    private Color color;
    private int id;

    public Checker(Color color, Point point, int id) {
        super(color == Color.BLACK ? "blackchecker" : "redchecker", point);
        this.color = color;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Checker that = (Checker) o;

        if (id != that.id)
            return false;

        if (color != that.color)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public void setPosition(Point p) {
        set(fImage, p);
    }
}
