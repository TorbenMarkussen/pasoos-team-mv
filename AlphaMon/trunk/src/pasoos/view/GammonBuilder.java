package pasoos.view;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.HotGammonFactory;

public interface GammonBuilder {
    void setGameType(Class<? extends HotGammonFactory> gameFactoryType);

    void setPlayer(Color color, PlayerType playerType, String name);

    void addPiece(Location loc, Color color);
}
