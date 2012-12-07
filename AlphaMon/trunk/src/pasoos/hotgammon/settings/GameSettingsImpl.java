package pasoos.hotgammon.settings;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.factory.SemiMonFactory;

import java.util.Properties;

public class GameSettingsImpl implements GameSettings {


    public GameSettingsImpl(String[] args) {
    }

    @Override
    public Class<? extends HotGammonFactory> getGameFactoryType() {
        Properties systemProperties = System.getProperties();

        Class defaultClass = SemiMonFactory.class;

        String clazzname = systemProperties.getProperty("gammon.type", defaultClass.getSimpleName());

        Class<? extends HotGammonFactory> factoryClazz;
        try {
            factoryClazz = (Class<? extends HotGammonFactory>) Class.forName(clazzname);
        } catch (ClassNotFoundException e) {
            factoryClazz = defaultClass;
        }

        return factoryClazz;
    }

    @Override
    public PlayerType getPlayerType(Color color) {
        if (color == Color.BLACK)
            return PlayerType.AIPlayer;

        //return PlayerType.DummyPlayer;
        return PlayerType.ManualPlayer;
    }

    @Override
    public String getName(Color color) {
        return color.toString();
    }
}
