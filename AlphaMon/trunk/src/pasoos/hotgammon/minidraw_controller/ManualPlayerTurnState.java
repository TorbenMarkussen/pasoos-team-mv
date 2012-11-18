package pasoos.hotgammon.minidraw_controller;

import minidraw.standard.SelectionTool;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.minidraw_view.Checker;
import pasoos.physics.Convert;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ManualPlayerTurnState extends GameControllerStateImpl {
    private SelectionTool selectionTool;
    private boolean isDragging;
    private Location moveFrom;
    private Location moveTo;
    private Checker movingChecker;

    public ManualPlayerTurnState(GameContext context) {
        super(context);
        selectionTool = new SelectionTool(editor);
        isDragging = false;

    }

    @Override
    public void entry() {
        evaluateState();
    }

    private void evaluateState() {
        int[] diceValuesLeft = hgvm.diceValuesLeft();
        if (diceValuesLeft.length == 0) {
            gameContext.setState(new NextTurnGameState(gameContext));
        } else if (hgvm.winner() != Color.NONE) {
            gameContext.setState(new WinnerFoundState(gameContext));
        } else {
            gameContext.updateStatusText(hgvm.getPlayerInTurn() + " player has " + diceValuesLeft.length + " moves left");
        }

    }

    @Override
    public void exit() {
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent, int i, int i1) {
        if (hgvm.isMoveable(mouseEvent.getX(), mouseEvent.getY())) {
            movingChecker = hgvm.getChecker(mouseEvent.getX(), mouseEvent.getY());
            moveFrom = Convert.xy2Location(mouseEvent.getX(), mouseEvent.getY());
            selectionTool.mouseDown(mouseEvent, i, i1);
            isDragging = true;
        }
        evaluateState();
    }

    @Override
    public void mouseDrag(MouseEvent mouseEvent, int i, int i1) {
        if (isDragging)
            selectionTool.mouseDrag(mouseEvent, i, i1);

        evaluateState();
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int i, int i1) {
        if (isDragging) {
            selectionTool.mouseUp(mouseEvent, i, i1);
            moveTo = Convert.xy2Location(mouseEvent.getX(), mouseEvent.getY());
            boolean moved = hgvm.move(moveFrom, moveTo);
            if (moved) {
                //hgvm.snapPosition(movingChecker, moveTo);
            } else {
                hgvm.restorePostion(movingChecker, moveFrom);
            }
            isDragging = false;
        }

        evaluateState();
    }

    @Override
    public void mouseMove(MouseEvent mouseEvent, int i, int i1) {
        if (isDragging)
            selectionTool.mouseMove(mouseEvent, i, i1);

        evaluateState();
    }

    @Override
    public void keyDown(KeyEvent keyEvent, int i) {
        if (isDragging)
            selectionTool.keyDown(keyEvent, i);

        evaluateState();
    }
}
