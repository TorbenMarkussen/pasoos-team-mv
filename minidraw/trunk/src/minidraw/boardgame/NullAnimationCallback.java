package minidraw.boardgame;

public class NullAnimationCallback<LOCATION> implements AnimationCallbacks<LOCATION> {
    @Override
    public void beforeStart(LOCATION from, LOCATION to) {
    }

    @Override
    public void afterStart(LOCATION from, LOCATION to) {
    }

    @Override
    public void beforeEnd(LOCATION from, LOCATION to) {
    }

    @Override
    public void afterEnd(LOCATION from, LOCATION to) {
    }
}
