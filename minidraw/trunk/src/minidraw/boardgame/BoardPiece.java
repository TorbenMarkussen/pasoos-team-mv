package minidraw.boardgame;

import minidraw.framework.Figure;

public interface BoardPiece extends Figure {
    void changeImage(String imageName);

    boolean isMobile();

    boolean performAction(int fromX, int fromY, int toX, int toY);

    void setMobile(boolean mobile);
}
