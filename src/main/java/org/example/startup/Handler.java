package org.example.startup;

public abstract class Handler {
    Handler nextHandler;
    FileManager fileManager;

    public Handler(Handler nextHandler, FileManager fileManager){
        this.nextHandler = nextHandler;
        this.fileManager = fileManager;
    }

    public boolean handle(Request request){
        boolean success = handleRequest(request, fileManager);
        if(!success){
            return false;
        }
        if(nextHandler != null){
            return nextHandler.handleRequest(request, fileManager);
        }
        return true;
    }

    protected abstract boolean handleRequest(Request request, FileManager fileManager);
}
