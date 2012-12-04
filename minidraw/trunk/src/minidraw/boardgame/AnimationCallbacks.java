package minidraw.boardgame;

public interface AnimationCallbacks<LOCATION> {
    void beforeStart(LOCATION from, LOCATION to);

    void afterStart(LOCATION from, LOCATION to);

    void beforeEnd(LOCATION from, LOCATION to);

    void afterEnd(LOCATION from, LOCATION to);
}
