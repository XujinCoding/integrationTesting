package com.xujin.chain_of_responsibility;

public class InfoMessage extends AbstractLogger{

    public InfoMessage(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
