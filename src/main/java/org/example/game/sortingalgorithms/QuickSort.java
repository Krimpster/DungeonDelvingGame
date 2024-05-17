package org.example.game.sortingalgorithms;

import org.example.interfaces.ISortingOption;
import org.example.objects.Leaderboard;

import java.util.ArrayList;
import java.util.Collections;

public class QuickSort implements ISortingOption {
    @Override
    public ArrayList<Leaderboard> sort(ArrayList<Leaderboard> leaderboards){
        return quickSort(leaderboards, 0 , leaderboards.size() - 1);
    }

    private ArrayList<Leaderboard> quickSort(ArrayList<Leaderboard> leaderboards){
        if(leaderboards.size() <= 1){
            return leaderboards;
        }

        int middleIndex = (int)Math.ceil((double)leaderboards.size() / 2);
        Leaderboard pivot = leaderboards.get(middleIndex);

        ArrayList<Leaderboard> less = new ArrayList<>();
        ArrayList<Leaderboard> greater = new ArrayList<>();

        for(int i = 0; i < leaderboards.size(); i++){
            if(leaderboards.get(i).getScore() < pivot.getScore()){
                if(i == middleIndex){
                    continue;
                }
                less.add(leaderboards.get(i));
            }
            else {
                greater.add(leaderboards.get(i));
            }
        }
        return joinLists(less, greater);
    }

   private ArrayList<Leaderboard> quickSort(ArrayList<Leaderboard> leaderboards, int min, int max) {
        if (min < max) {
            int pivotIndex = partition(leaderboards, min, max);
            quickSort(leaderboards, min, pivotIndex - 1);
            quickSort(leaderboards, pivotIndex + 1, max);
        }
        return leaderboards;
    }

    private void swap(ArrayList<Leaderboard> leaderboards, int index1, int index2) {
        Collections.swap(leaderboards, index1, index2);
    }

    private int partition(ArrayList<Leaderboard> leaderboards, int min, int max) {
        int pivotIndex = max;
        Leaderboard pivotLeaderboard = leaderboards.get(pivotIndex);

        int middleIndex = min;
        swap(leaderboards, pivotIndex, max);
        for(int i = min; i < max; i++) {
            if(leaderboards.get(i).getScore() < pivotLeaderboard.getScore()) {
                swap(leaderboards, middleIndex, i);
                middleIndex++;
            }
        }
        swap(leaderboards, middleIndex, max);
        return middleIndex;
    }


    private ArrayList<Leaderboard> joinLists(ArrayList<Leaderboard> less, ArrayList<Leaderboard> greater){
        ArrayList<Leaderboard> sortedLeaderboard = new ArrayList<>();

        for(Leaderboard l : less){
            sortedLeaderboard.add(l);
        }

        for (Leaderboard l : greater){
            sortedLeaderboard.add(l);
        }

        return sortedLeaderboard;
    }
}
