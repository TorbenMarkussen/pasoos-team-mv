package pasoos.hotgammon.ui;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.settings.GameSettings;

import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;

public class GammonBuildDirector {
    private GameSettings settings;
    private GammonBuilder gammonBuilder;

    public GammonBuildDirector(GameSettings gs, GammonBuilder gammonBuilder) {
        settings = gs;
        this.gammonBuilder = gammonBuilder;
    }

    public void construct() {
        gammonBuilder.setGameType(settings.getGameFactoryType());
        gammonBuilder.setPlayer(RED, settings.getPlayerType(RED), settings.getName(RED));
        gammonBuilder.setPlayer(BLACK, settings.getPlayerType(BLACK), settings.getName(BLACK));

        Game game = gammonBuilder.getGame();

        for (Location loc : Location.values()) {
            Color color = game.getColor(loc);
            int count = game.getCount(loc);

            for (int i = 0; i < count; i++) {
                if (color == Color.BLACK)
                    gammonBuilder.addPiece(Location.B_BEAR_OFF, color);
                else
                    gammonBuilder.addPiece(Location.R_BEAR_OFF, color);
            }
        }
        gammonBuilder.addDie("die1");
        gammonBuilder.addDie("die2");
    }

}
