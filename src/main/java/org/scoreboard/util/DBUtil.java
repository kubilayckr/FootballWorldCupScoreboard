package org.scoreboard.util;

import org.scoreboard.model.entity.Match;
import org.scoreboard.model.index.ScoreboardIndex;
import org.scoreboard.util.comparator.ScoreboardCustomComparator;

import java.util.*;

public class DBUtil {

    private static final SortedMap<ScoreboardIndex, Match> TABLE_SCOREBOARD = new TreeMap<>(new ScoreboardCustomComparator());
    private static final Map<UUID, Match> TABLE_MATCH = new TreeMap<>();

    private DBUtil() {
    }

    public static SortedMap<ScoreboardIndex, Match> getTableScoreboard() {
        return TABLE_SCOREBOARD;
    }

    public static Map<UUID, Match> getTableMatch() {
        return TABLE_MATCH;
    }
}
