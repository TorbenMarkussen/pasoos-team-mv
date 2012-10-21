package pasoos.hotgammon.rules.validator;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gameengine.BoardState;
import pasoos.hotgammon.rules.MoveValidatorStrategy;

public class BackgammonMoveValidatorStrategy implements MoveValidatorStrategy {
    private BoardState board;

    public BackgammonMoveValidatorStrategy(BoardState board) {
        this.board = board;
    }

    @Override
    public boolean isValidMove(Location fromLocation, Location toLocation, int dice) {
        Color fromColor = board.getColor(fromLocation);
        Color toColor = board.getColor(toLocation);
        Color opponentColor = fromColor.getOpponentColor();
        int moveDistance = Math.abs(Location.distance(fromLocation, toLocation));

        if (fromColor == Color.NONE)
            return false;

        if (isInValidDirection(fromLocation, toLocation))
            return false;

        if (toLocation != Location.getBearOff(fromColor)) {
            if (dice != moveDistance)
                return false;
        }
        int foundDice = moveDistance;

        if (toColor == opponentColor) {
            if (board.getCount(toLocation) > 1)
                return false;
        }

        if (hasBarCheckers(fromColor) && (fromLocation != fromColor.getBar()))
            return false;

        if ((toLocation == Location.getBearOff(fromColor))) {
            //bearoff move
            if (!board.hasAllInInnerTable(fromColor))
                return false;

            if (dice == moveDistance) {
                foundDice = moveDistance;
            } else {
                if (dice > moveDistance) {
                    Location l = getLocationFromBearoffDistance(fromColor, dice);
                    if (board.getColor(l) == fromColor)
                        return false;
                }
                foundDice = dice;
            }
        }


        return foundDice != 0;
    }

    private boolean hasBarCheckers(Color fromColor) {
        return (board.getCount(fromColor.getBar()) > 0);
    }

    private boolean isInValidDirection(Location fromLocation, Location toLocation) {
        Color colorGivenByDirection = Color.getColorFromNumerical(Location.distance(fromLocation, toLocation));
        return colorGivenByDirection != board.getColor(fromLocation);
    }

    private Location getLocationFromBearoffDistance(Color color, int distance) throws IllegalArgumentException {
        Location location = Location.getBearOff(color);
        if (location == Location.R_BEAR_OFF) {
            if (distance == 1) return Location.R1;
            if (distance == 2) return Location.R2;
            if (distance == 3) return Location.R3;
            if (distance == 4) return Location.R4;
            if (distance == 5) return Location.R5;
            if (distance == 6) return Location.R6;
        }
        if (location == Location.B_BEAR_OFF) {
            if (distance == 1) return Location.B1;
            if (distance == 2) return Location.B2;
            if (distance == 3) return Location.B3;
            if (distance == 4) return Location.B4;
            if (distance == 5) return Location.B5;
            if (distance == 6) return Location.B6;
        }
        throw new IllegalArgumentException("Location should be a bearoff location");
    }
}
