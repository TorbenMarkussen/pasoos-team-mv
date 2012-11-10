package snakesladders.view;

import minidraw.boardgame.PropAppearanceStrategy;
import snakesladders.domain.Game;

public class SnakesAndLaddersPropAppearanceStrategy implements PropAppearanceStrategy {

    private Game game;

    public SnakesAndLaddersPropAppearanceStrategy(Game game) {
        this.game = game;
    }

    public String calculateImageNameForPropWithKey(String key) {
        return "die" + game.getDieValue();
    }
}
