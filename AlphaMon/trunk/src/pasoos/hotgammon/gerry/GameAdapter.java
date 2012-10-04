package pasoos.hotgammon.gerry;

public interface GameAdapter {
    int[] getBoard();

    int[] getDices();

    void move(int from, int to);
}
