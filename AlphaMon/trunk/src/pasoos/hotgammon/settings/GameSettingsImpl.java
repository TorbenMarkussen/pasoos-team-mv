package pasoos.hotgammon.settings;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.factory.SemiMonFactory;

public class GameSettingsImpl implements GameSettings {
    public GameSettingsImpl(String[] args) {
    }

    @Override
    public Class<? extends HotGammonFactory> getGameFactoryType() {
        return SemiMonFactory.class;
        //return AlphaMonFactory.class;
    }

    @Override
    public PlayerType getPlayerType(Color color) {
        if (color == Color.BLACK)
            return PlayerType.AIPlayer;
        //return PlayerType.ManualPlayer;

        return PlayerType.ManualPlayer;
    }

    @Override
    public String getName(Color color) {
        return color.toString();
    }
}
