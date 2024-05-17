package org.example.objects;

import org.example.objects.player.PlayerCharacter;

public class Leaderboard {
    private String playerName;
    private String playerClass;
    private int score;
    private int turnsTaken;
    private int monstersDefeated;

    public Leaderboard(String playerName, String playerClass, int score, int turnsTaken, int monstersDefeated){
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.score = score;
        this.turnsTaken = turnsTaken;
        this.monstersDefeated = monstersDefeated;
    }

    public Leaderboard(String playerName, String playerClass, int turnsTaken, int monstersDefeated){
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.turnsTaken = turnsTaken;
        this.monstersDefeated = monstersDefeated;
    }

    public String getCSV(){
        return playerName + "," + playerClass + "," + score + "," + turnsTaken + "," + monstersDefeated;
    }

    public String getDetails(){
        return "Player Name: " + playerName + " | Player Class: " + playerClass + " | Score Achieved: " + score +
                " | Monsters Defeated During The Run: " + monstersDefeated +
                " | Turns Taken During The Run: " + turnsTaken;
    }

    public void calculateScore(PlayerCharacter playerCharacter, int turnsTaken){
        int scoreTotal = playerCharacter.getScoreTotal();
        int expGained = playerCharacter.getExperienceGained() * 500;
        int scoreMonsters = playerCharacter.getMonstersDefeated() * 2000;

        this.score = (scoreTotal + expGained + scoreMonsters) / turnsTaken;
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
