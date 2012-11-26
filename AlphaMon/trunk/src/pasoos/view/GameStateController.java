package pasoos.view;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;

import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;

public class GameStateController implements GameObserver {
    private Game game;
    private GammonPlayer redPlayer;
    private GammonPlayer blackPlayer;

    public GameStateController() {
        game = null;
    }

    @Override
    public void checkerMove(Location from, Location to) {
    }

    @Override
    public void diceRolled(int[] values) {
    }

    @Override
    public void turnEnded() {
        Color playerTurnEnded = game.getPlayerInTurn();
        if (playerTurnEnded == RED) {
            redPlayer.setInactive();
            blackPlayer.setActive();
        } else if (playerTurnEnded == BLACK) {
            blackPlayer.setInactive();
            redPlayer.setActive();
        }
    }

    public GameStateController setGame(Game game) {
        if (game != null) {
            game.removeObserver(this);
        }
        this.game = game;
        game.addObserver(this);
        return this;
    }

    public GameStateController setRedPlayer(GammonPlayer player) {
        redPlayer = player;
        return this;
    }

    public GameStateController setBlackPlayer(GammonPlayer player) {
        blackPlayer = player;
        return this;
    }
}
