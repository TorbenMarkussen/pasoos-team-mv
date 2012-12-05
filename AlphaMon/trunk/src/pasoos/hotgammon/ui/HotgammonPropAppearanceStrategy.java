package pasoos.hotgammon.ui;

import minidraw.boardgame.PropAppearanceStrategy;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;

public class HotgammonPropAppearanceStrategy implements PropAppearanceStrategy {
    private Game game;

    public HotgammonPropAppearanceStrategy(Game game) {
        this.game = game;
    }

    @Override
    public String calculateImageNameForPropWithKey(String keyOfProp) {
        String diePrefix;

        if ((game.getPlayerInTurn() == Color.RED && game.getNumberOfMovesLeft() != 0) ||
                (game.getPlayerInTurn() == Color.BLACK && game.getNumberOfMovesLeft() == 0))
            diePrefix = "rdie";
        else
            diePrefix = "bdie";

        if (keyOfProp.equals("die1")) {
            return diePrefix + game.diceThrown()[0];
        } else if (keyOfProp.equals("die2")) {
            return diePrefix + game.diceThrown()[1];
        }
        return null;

    }
}
