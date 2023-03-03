package com.xujin.chain_of_responsibility;

public class DebugMessage extends AbstractLogger{

    public DebugMessage(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Debug Console::Logger: " + message);
    }
}
