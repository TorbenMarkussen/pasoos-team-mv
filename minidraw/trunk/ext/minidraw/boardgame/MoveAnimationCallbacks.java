package minidraw.boardgame;

public interface MoveAnimationCallbacks<LOCATION> {
    void beforeMoveStart(LOCATION from, LOCATION to);

    void afterMoveStart(LOCATION from, LOCATION to);

    void beforeMoveEnd(LOCATION from, LOCATION to);

    void afterMoveEnd(LOCATION from, LOCATION to);
}
