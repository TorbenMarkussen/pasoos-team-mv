package snakesladders.domain;

public class Square {
    private int index;

    public Square(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Square square = (Square) o;

        if (index != square.index)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return index;
    }
}
