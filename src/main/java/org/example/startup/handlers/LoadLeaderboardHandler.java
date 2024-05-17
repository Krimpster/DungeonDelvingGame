package org.example.startup.handlers;

import org.example.startup.FileManager;
import org.example.startup.Handler;
import org.example.startup.Request;

public class LoadLeaderboardHandler extends Handler {
    public LoadLeaderboardHandler(Handler nextHandler, FileManager fileManager){
        super(nextHandler, fileManager);
    }
    @Override
    protected boolean handleRequest(Request request, FileManager fileManager){
        fileManager.loadLeaderBoard(request.getLeaderboardFilePath());
        System.out.println("Leaderboard loaded.\n\n");
        return true;
    }
}
