package org.example.startup.handlers;

import org.example.startup.FileManager;
import org.example.startup.Handler;
import org.example.startup.Request;

public class LoadMonstersHandler extends Handler {
    public LoadMonstersHandler(Handler nextHandler, FileManager fileManager){
        super(nextHandler, fileManager);
    }

    @Override
    protected boolean handleRequest(Request request, FileManager fileManager){
        fileManager.loadMonsters(request.getBestiaryFilePath());
        System.out.println("\nMonsters loaded.");
        return true;
    }
}
