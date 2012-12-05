package pasoos.hotgammon.boardphysics;

import pasoos.hotgammon.Location;

import java.awt.*;
import java.util.HashMap;
import java.util.Set;

public class BoardPhysics {
    private static Rectangle board = new Rectangle(0, 0, 594, 438);
    private static HashMap<Location, Rectangle> logicalLocations = new HashMap<Location, Rectangle>();

    static {
        logicalLocations.put(Location.B12, new Rectangle(15 + 0 * 40, 20, 40, 200));
        logicalLocations.put(Location.B11, new Rectangle(15 + 1 * 40, 20, 40, 200));
        logicalLocations.put(Location.B10, new Rectangle(15 + 2 * 40, 20, 40, 200));
        logicalLocations.put(Location.B9, new Rectangle(15 + 3 * 40, 20, 40, 200));
        logicalLocations.put(Location.B8, new Rectangle(15 + 4 * 40, 20, 40, 200));
        logicalLocations.put(Location.B7, new Rectangle(15 + 5 * 40, 20, 40, 200));

        logicalLocations.put(Location.B6, new Rectangle(300 + 0 * 40, 20, 40, 200));
        logicalLocations.put(Location.B5, new Rectangle(300 + 1 * 40, 20, 40, 200));
        logicalLocations.put(Location.B4, new Rectangle(300 + 2 * 40, 20, 40, 200));
        logicalLocations.put(Location.B3, new Rectangle(300 + 3 * 40, 20, 40, 200));
        logicalLocations.put(Location.B2, new Rectangle(300 + 4 * 40, 20, 40, 200));
        logicalLocations.put(Location.B1, new Rectangle(300 + 5 * 40, 20, 40, 200));

        // red normal points
        logicalLocations.put(Location.R12, new Rectangle(15 + 0 * 40, 217, 40, 200));
        logicalLocations.put(Location.R11, new Rectangle(15 + 1 * 40, 217, 40, 200));
        logicalLocations.put(Location.R10, new Rectangle(15 + 2 * 40, 217, 40, 200));
        logicalLocations.put(Location.R9, new Rectangle(15 + 3 * 40, 217, 40, 200));
        logicalLocations.put(Location.R8, new Rectangle(15 + 4 * 40, 217, 40, 200));
        logicalLocations.put(Location.R7, new Rectangle(15 + 5 * 40, 217, 40, 200));

        logicalLocations.put(Location.R6, new Rectangle(300 + 0 * 40, 217, 40, 200));
        logicalLocations.put(Location.R5, new Rectangle(300 + 1 * 40, 217, 40, 200));
        logicalLocations.put(Location.R4, new Rectangle(300 + 2 * 40, 217, 40, 200));
        logicalLocations.put(Location.R3, new Rectangle(300 + 3 * 40, 217, 40, 200));
        logicalLocations.put(Location.R2, new Rectangle(300 + 4 * 40, 217, 40, 200));
        logicalLocations.put(Location.R1, new Rectangle(300 + 5 * 40, 217, 40, 200));

        // black special points
        logicalLocations.put(Location.B_BAR, new Rectangle(260, 220, 36, 200));
        logicalLocations.put(Location.B_BEAR_OFF, new Rectangle(545, 12, 40, 200));

        // red special points
        logicalLocations.put(Location.R_BAR, new Rectangle(260, 12, 36, 200));
        logicalLocations.put(Location.R_BEAR_OFF, new Rectangle(545, 220, 40, 200));
    }


    public static Location map2Location(int x, int y) {
        Set<Location> s = logicalLocations.keySet();
        for (Location l : s) {
            Rectangle r = logicalLocations.get(l);
            if (r.contains(x, y)) {
                return l;
            }
        }
        return null;
    }

    public static Rectangle getRectangle(Location location) {
        return logicalLocations.get(location);
    }
}
