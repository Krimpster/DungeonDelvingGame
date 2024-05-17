package org.example.game.sortingalgorithms;

import org.example.interfaces.ISortingOption;
import org.example.objects.Leaderboard;

import java.util.ArrayList;
import java.util.Collections;

public class InsertionSort implements ISortingOption {
    @Override
    public ArrayList<Leaderboard> sort(ArrayList<Leaderboard> leaderboards) {
        for (int i = 1; i < leaderboards.size(); i++) {
            Leaderboard leaderboardToSort = leaderboards.get(i);
            int j = i;
            while (j > 0) {
                if (leaderboards.get(j - 1).getScore() > leaderboardToSort.getScore()) {
                    Collections.swap(leaderboards, j, j - 1);
                    j--;
                } else {
                    break;
                }
            }
            Collections.swap(leaderboards, j, i);
        }
        return leaderboards;
    }
}
