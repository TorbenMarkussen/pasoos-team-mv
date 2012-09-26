package pasoos.hotgammon.gamelogger;

import pasoos.hotgammon.Game;

public interface GameLogDecorator extends Game {
    void addLogObserver(LogObserver logObserver);
}
