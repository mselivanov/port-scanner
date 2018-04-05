package com.github.mselivanov.portscanner;

public enum OutputDestination {
    CONSOLE("console"),
    FILE("file"),
    UNKNOWN("unknown");
    
    private final String text;
    
    OutputDestination(final String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return this.text;
    }
}