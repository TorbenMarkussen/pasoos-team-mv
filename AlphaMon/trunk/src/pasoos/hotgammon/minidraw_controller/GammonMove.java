package pasoos.hotgammon.minidraw_controller;

import pasoos.hotgammon.Location;

public class GammonMove {
    private final Location from;
    private final Location to;

    public GammonMove(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    @Override
    public String toString() {
        return from + " -> " + to;
    }
}
