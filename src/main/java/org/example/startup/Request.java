package org.example.startup;

import java.io.File;

public class Request {
    private final String bestiaryFilePath = "src/main/resources/saved_files/bestiary";
    private final String equipmentFilePath = "src/main/resources/saved_files/equipment";
    private final String leaderboardFilePath = "src/main/resources/saved_files/leaderboard";

    public Request(){}

    public String getBestiaryFilePath() {
        return bestiaryFilePath;
    }

    public String getEquipmentFilePath() {
        return equipmentFilePath;
    }

    public String getLeaderboardFilePath() {
        return leaderboardFilePath;
    }
}
