package org.scoreboard.util.comparator;

import org.scoreboard.model.index.ScoreboardIndex;

import java.util.Comparator;

public class ScoreboardCustomComparator implements Comparator<ScoreboardIndex> {
    public int compare(ScoreboardIndex s1, ScoreboardIndex s2)
    {
        return s2.getMatchStartDate().compareTo(s1.getMatchStartDate());
    }
}
