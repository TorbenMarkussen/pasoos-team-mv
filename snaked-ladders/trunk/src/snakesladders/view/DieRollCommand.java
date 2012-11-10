package snakesladders.view;

import snakesladders.domain.*;

import minidraw.boardgame.*;

public class DieRollCommand implements Command {
  private Game game;
  public DieRollCommand(Game game) {
    this.game = game;
  }
  public boolean execute() {
    game.rollDie();
    return true;
  }
  public void setFromCoordinates(int fromX, int fromY) {}
  public void setToCoordinates(int toX, int toY) {}
}