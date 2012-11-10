package snakesladders.domain;

import minidraw.boardgame.BoardGameObserver;
import snakesladders.view.Constant;

public class GameImpl implements Game {
    private int die = 1;

    public void rollDie() {
        die = (int) (Math.random() * 6 + 1);
        observer.propChangeEvent(Constant.diePropName);
    }

    public int getDieValue() {
        return die;
    }

    private Square tokenSquare = new Square(1);

    public boolean move(int player, Square fromSquare, Square toSquare) {
        System.out.println("Moving to " + toSquare.index());
        tokenSquare = toSquare;
        observer.pieceMovedEvent(fromSquare, toSquare);
        return true;
    }

    private BoardGameObserver<Square> observer;

    public void addObserver(BoardGameObserver<Square> observer) {
        this.observer = observer;
    }
}
