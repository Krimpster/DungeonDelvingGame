package org.example.interfaces;

import org.example.objects.Leaderboard;

import java.util.ArrayList;

public interface ISortingOption {
    ArrayList<Leaderboard> sort(ArrayList<Leaderboard> leaderboards);
}
