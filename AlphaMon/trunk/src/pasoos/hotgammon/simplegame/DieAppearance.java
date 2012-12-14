package pasoos.hotgammon.simplegame;

import minidraw.boardgame.PropAppearanceStrategy;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;

public class DieAppearance implements PropAppearanceStrategy {

    private Game game;

    public DieAppearance(Game game) {

        this.game = game;
    }

    @Override
    public String calculateImageNameForPropWithKey(String keyOfProp) {
        int[] dice = game.diceThrown();
        String dieNamePrefix = game.getPlayerInTurn() == Color.BLACK ? "bdie" : "rdie";
        if (keyOfProp.equals("die1")) {
            return dieNamePrefix + dice[0];
        }
        if (keyOfProp.equals("die2")) {
            return dieNamePrefix + dice[1];
        }
        return "";
    }
}
