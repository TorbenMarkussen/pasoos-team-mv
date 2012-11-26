package pasoos.view;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.factory.SemiMonFactory;

public class GameSettingsImpl implements GameSettings {
    public GameSettingsImpl(String[] args) {
    }

    @Override
    public Class<? extends HotGammonFactory> getGameFactoryType() {
        return SemiMonFactory.class;
    }

    @Override
    public PlayerType getPlayerType(Color color) {
        return PlayerType.ManualPlayer;
    }

    @Override
    public String getName(Color color) {
        return color.toString();
    }
}
