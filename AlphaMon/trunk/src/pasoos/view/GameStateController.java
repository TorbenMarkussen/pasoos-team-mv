package pasoos.view;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;

import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;

public class GameStateController implements GameObserver {
    private final Game game;
    private final GammonPlayer redPlayer;
    private final GammonPlayer blackPlayer;

    public GameStateController(Game game, GammonPlayer redPlayer, GammonPlayer blackPlayer) {
        this.game = game;
        this.redPlayer = redPlayer;
        this.blackPlayer = blackPlayer;
        game.addObserver(this);

        redPlayer.setInactive();
        blackPlayer.setActive();
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
}
