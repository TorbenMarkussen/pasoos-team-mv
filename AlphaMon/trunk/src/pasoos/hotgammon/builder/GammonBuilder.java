package pasoos.hotgammon.builder;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.settings.PlayerType;

public interface GammonBuilder {
    void setGameType(Class<? extends HotGammonFactory> gameFactoryType);

    void setPlayer(Color color, PlayerType playerType, String name);

    void addPiece(Location loc, Color color);

    Game getGame();

    void addDie(String dieName);
}
