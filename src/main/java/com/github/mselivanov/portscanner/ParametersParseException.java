package com.github.mselivanov.portscanner;

public class ParametersParseException extends Exception {
 
    public ParametersParseException() {
        super();
    }
    
    public ParametersParseException(String message) {
        super(message);
    }

    public ParametersParseException(Exception exception) {
        super(exception);
    }
    
}