package pasoos.view;

import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.Command;
import minidraw.framework.SoundResource;
import pasoos.hotgammon.Game;

public class GammonDice implements Command {

    private Game game;
    private State state;
    private BoardPiece die1;
    private BoardPiece die2;

    public GammonDice(Game game, State state) {
        this.game = game;
        this.state = state;
    }

    public void addDie1(BoardPiece piece) {
        die1 = piece;
    }

    public void addDie2(BoardPiece piece) {

        die2 = piece;
    }

    @Override
    public void setFromCoordinates(int fromX, int fromY) {
    }

    @Override
    public void setToCoordinates(int toX, int toY) {
    }

    @Override
    public boolean execute() {
        state.rollDiceRequest();
        return true;
    }

}
