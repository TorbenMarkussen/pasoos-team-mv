package minidraw.animatedboard;

import minidraw.boardgame.BoardGameDrawing;

public interface AnimatedBoard<LOCATION> extends BoardGameDrawing<LOCATION> {
    public void moveAnimated(final LOCATION from, final LOCATION to, final MoveAnimationCallbacks<LOCATION> cb);
}
