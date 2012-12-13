package minidraw.animatedboard;

public class NullMoveAnimationCallback<LOCATION> implements MoveAnimationCallbacks<LOCATION> {
    @Override
    public void beforeMoveStart(LOCATION from, LOCATION to) {
    }

    @Override
    public void afterMoveStart(LOCATION from, LOCATION to) {
    }

    @Override
    public void beforeMoveEnd(LOCATION from, LOCATION to) {
    }

    @Override
    public void afterMoveEnd(LOCATION from, LOCATION to) {
    }
}
