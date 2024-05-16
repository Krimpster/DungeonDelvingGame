package org.example.objects;

import org.example.objects.player.PlayerCharacter;

public class Leaderboard {
    private String playerName;
    private String playerClass;
    private int score;
    private int turnsTaken;

    public Leaderboard(String playerName, String playerClass, int score, int turnsTaken){
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.score = score;
        this.turnsTaken = turnsTaken;
    }

    public Leaderboard(String playerName, String playerClass, int turnsTaken){
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.turnsTaken = turnsTaken;
    }

    public Leaderboard(){

    }

    public String getCSV(){
        return playerName + "," + playerClass + "," + score + "," + turnsTaken;
    }

    public void calculateScore(PlayerCharacter playerCharacter, int turnsTaken){
        int scoreTotal = playerCharacter.getScoreTotal();
        int expGained = playerCharacter.getExperienceGained() * 500;

        this.score = (scoreTotal + expGained) / turnsTaken;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTurnsTaken() {
        return turnsTaken;
    }

    public void setTurnsTaken(int turnsTaken) {
        this.turnsTaken = turnsTaken;
    }
}
