package org.example.game.sortingalgorithms;

import org.example.objects.Leaderboard;

import java.util.ArrayList;

public class NameInsertionSort {
    private ArrayList<Leaderboard> leaderboards;
    public NameInsertionSort(ArrayList<Leaderboard> leaderboards){
        this.leaderboards = leaderboards;
    }

    public ArrayList<Leaderboard> sortByName(){
        for(int i = 1; i < leaderboards.size(); i++){
            Leaderboard entryToSort = leaderboards.get(i);
            int j = i;
            while(j > 0){
                if (leaderboards.get(j-1).getPlayerName().compareTo(entryToSort.getPlayerName()) > 0){
                    leaderboards.set(j, leaderboards.get(j-1));
                    j--;
                }
                else{
                    break;
                }
            }
            leaderboards.set(j, entryToSort);
        }
        return leaderboards;
    }
}
