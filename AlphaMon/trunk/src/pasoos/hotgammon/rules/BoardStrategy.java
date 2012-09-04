package pasoos.hotgammon.rules;

import pasoos.hotgammon.Location;

import java.util.EnumMap;

public interface BoardStrategy {
    EnumMap<Location, Integer> create();
}
