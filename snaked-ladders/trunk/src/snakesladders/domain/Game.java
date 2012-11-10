package snakesladders.domain;

import minidraw.boardgame.BoardGameObserver;

public interface Game {
    public void rollDie();

    public int getDieValue();

    public boolean move(int player, Square fromSquare, Square toSquare);

    public void addObserver(BoardGameObserver<Square> observer);
}
