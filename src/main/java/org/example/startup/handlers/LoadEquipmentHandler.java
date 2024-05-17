package org.example.startup.handlers;

import org.example.startup.FileManager;
import org.example.startup.Handler;
import org.example.startup.Request;

public class LoadEquipmentHandler extends Handler {
    public LoadEquipmentHandler(Handler nextHandler, FileManager fileManager){
        super(nextHandler, fileManager);
    }

    @Override
    protected boolean handleRequest(Request request, FileManager fileManager){
        fileManager.loadEquipment(request.getEquipmentFilePath());
        System.out.println("Equipment loaded.");
        return true;
    }
}
