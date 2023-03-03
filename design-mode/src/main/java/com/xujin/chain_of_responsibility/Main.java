package com.xujin.chain_of_responsibility;

public class Main {
    private static AbstractLogger getLoggerChain(){
        AbstractLogger errorLogger = new ErrorMessage(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new DebugMessage(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new InfoMessage(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }
    public static void main(String[] args) {
        AbstractLogger loggerChain = getLoggerChain();
        loggerChain.logMessage(AbstractLogger.INFO," 这个是 Info");
        loggerChain.logMessage(AbstractLogger.DEBUG," 这个是 Debug");
        loggerChain.logMessage(AbstractLogger.ERROR," 这个是 Error");
    }
}
