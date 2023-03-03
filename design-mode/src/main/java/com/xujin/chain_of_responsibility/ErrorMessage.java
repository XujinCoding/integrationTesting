package com.xujin.chain_of_responsibility;

public class ErrorMessage extends AbstractLogger{

    public ErrorMessage(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
