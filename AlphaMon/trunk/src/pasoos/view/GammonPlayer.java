package pasoos.view;

import minidraw.boardgame.Command;

public interface GammonPlayer extends Command {
    void setActive();

    void setInactive();

    void addStatusObserver(StatusObserver statusObserver);
}
