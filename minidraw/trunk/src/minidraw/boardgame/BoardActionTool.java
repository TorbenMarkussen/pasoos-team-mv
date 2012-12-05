package minidraw.boardgame;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;

import java.awt.event.MouseEvent;

/**
 * A tool to handle user interaction on board game figures.
 * Responsibility:
 * A) To visually move instances of BoardFigure objects.
 * B) To allow clicking immovable BoardFigures ("props").
 * C) To execute the board figure's 'performAction'
 * method upon mouse up.
 * <p/>
 * Collaborators: BoardFigure.
 * <p/>
 * Note: This tool will only move/click instances of
 * BoardFigure, for all other types of figures the tool
 * does nothing.
 * <p/>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 * <p/>
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com/
 */

public class BoardActionTool extends AbstractTool {
    /**
     * mouse position of the mouse up event
     */
    private int fLastX, fLastY;
    /**
     * mouse position of the mouse down event
     */
    private int fStartX, fStartY;

    private BoardPiece clickedFigure = null;
    private Figure draggedFigure = null;

    /**
     * Construct a tool with the given command
     */
    public BoardActionTool(DrawingEditor editor) {
        super(editor);
        clickedFigure = null;
        draggedFigure = null;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        fStartX = x;
        fStartY = y;
        // find the figure that was directly under the (x,y) clicked...
        BoardDrawing bd = (BoardDrawing) editor.drawing();
        BoardPiece piece = bd.findPiece(e.getX(), e.getY());
        if (piece != null) {
            clickedFigure = piece;
            fLastX = x;
            fLastY = y;
            if (clickedFigure.isMobile()) {
                editor.drawing().lock();
                piece.setZorder(10);
                draggedFigure = piece;
            }
        }
    }

    public void mouseDrag(MouseEvent e, int x, int y) {
        if (draggedFigure != null) {
            draggedFigure.moveBy(x - fLastX, y - fLastY);
            fLastX = x;
            fLastY = y;
        }
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        // ask the associated board figure to perform its associated action based
        // upon the coordinates of mouse down and mouse up (move or click)
        if (clickedFigure != null) {
            boolean valid = clickedFigure.performAction(fStartX, fStartY,
                    fLastX, fLastY);
            // if the figure's associate domain model tell the move is invalid
            // then move it back to its starting position.
            if (!valid && clickedFigure.isMobile()) {
                // move it back
                int dx = fStartX - x;
                int dy = fStartY - y;
                if (draggedFigure != null) {
                    draggedFigure.moveBy(dx, dy);
                }
            }
        }
        // unlock the drawing's concurrency lock
        if (draggedFigure != null) {
            editor.drawing().unlock();
        }
        clickedFigure = null;
        draggedFigure = null;
    }
}
