package pasoos.view;

import minidraw.boardgame.PropAppearanceStrategy;
import pasoos.hotgammon.Game;

public class HotgammonPropAppearanceStrategy implements PropAppearanceStrategy {
    private Game game;

    public HotgammonPropAppearanceStrategy(Game game) {
        this.game = game;
    }

    @Override
    public String calculateImageNameForPropWithKey(String keyOfProp) {
        if (keyOfProp.equals("die1")) {
            return "die" + game.diceThrown()[0];
        } else if (keyOfProp.equals("die2")) {
            return "die" + game.diceThrown()[1];
        }
        return null;

    }
}
