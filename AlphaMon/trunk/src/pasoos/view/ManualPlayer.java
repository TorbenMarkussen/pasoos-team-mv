package pasoos.view;

import minidraw.boardgame.BoardPiece;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.physics.Convert;

import java.util.ArrayList;
import java.util.List;

public class ManualPlayer implements GammonPlayer {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    private Game game;
    private String id;
    List<StatusObserver> statusObservers = new ArrayList<StatusObserver>();
    private List<BoardPiece> pieces = new ArrayList<BoardPiece>();

    public ManualPlayer(Game game, String id) {
        this.game = game;
        this.id = id;
    }

    @Override
    public void setFromCoordinates(int fromX, int fromY) {
        this.fromX = fromX;
        this.fromY = fromY;
    }

    @Override
    public void setToCoordinates(int toX, int toY) {
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public boolean execute() {
        Location from = Convert.xy2Location(fromX, fromY);
        Location to = Convert.xy2Location(toX, toY);
        System.out.println(id + " is moving " + from + " to " + to);
        return game.move(from, to);
    }

    @Override
    public void setActive() {
        for (BoardPiece p : pieces) {
            p.setMobile(true);
        }
        notifyStatus(id + " in turn");
    }

    private void notifyStatus(String s) {
        for (StatusObserver so : statusObservers) {
            so.updateStatus(s);
        }
    }

    @Override
    public void setInactive() {
        for (BoardPiece p : pieces) {
            p.setMobile(false);
        }
    }

    @Override
    public void addStatusObserver(StatusObserver statusObserver) {
        statusObservers.add(statusObserver);
    }

    @Override
    public void addPiece(BoardPiece piece) {
        pieces.add(piece);
    }
}
