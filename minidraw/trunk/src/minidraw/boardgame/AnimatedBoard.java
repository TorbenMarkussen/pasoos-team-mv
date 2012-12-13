package minidraw.boardgame;

public interface AnimatedBoard<LOCATION> extends BoardGameDrawing<LOCATION> {
    public void moveAnimated(final LOCATION from, final LOCATION to, final AnimationCallbacks<LOCATION> cb);
}
