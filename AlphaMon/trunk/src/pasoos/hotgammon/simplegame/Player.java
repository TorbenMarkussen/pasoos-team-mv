package pasoos.hotgammon.simplegame;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.ai.AIPlayer;
import pasoos.hotgammon.ai.GammonMove;

import java.util.List;

public class Player implements GameObserver {
    private Game game;
    private AIPlayer aiPlayer;
    private Color playerColor;

    public Player(Game game, AIPlayer aiPlayer, Color color) {
        this.game = game;
        this.aiPlayer = aiPlayer;
        playerColor = color;
    }

    @Override
    public void checkerMove(Location from, Location to) {
        checkPlayerTurnAndRollDice();
    }

    private void checkPlayerTurnAndRollDice() {
        if (game.getNumberOfMovesLeft() == 0) {
            if (game.getPlayerInTurn() == playerColor.getOpponentColor()) {
                invokeNextTurn();
            }
        }
    }

    protected void invokeNextTurn() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                game.nextTurn();
            }
        });
    }

    @Override
    public void diceRolled(int[] values) {
        if (game.getNumberOfMovesLeft() > 0) {
            if (game.getPlayerInTurn() == playerColor) {
                aiPlayer.play();
                List<GammonMove> moves = aiPlayer.getMoves();
                for (GammonMove move : moves) {
                    game.move(move.getFrom(), move.getTo());
                }
            }
        } else {
            checkPlayerTurnAndRollDice();
        }
    }
}
