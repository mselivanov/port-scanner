package com.github.mselivanov.portscanner;

public class ResultWriterFactory {
    
    private static final String OUTPUT_DESTINATION_ERROR_MSG = "%s destination isn't supported!";
    
    public static ResultWriter createResultWriter(PortScannerParameters parameters) {
        if (parameters.getOutputDestination() == OutputDestination.CONSOLE) {
            return new ConsoleResultWriter();
        } else if (parameters.getOutputDestination() == OutputDestination.FILE) {
            return new FileResultWriter(parameters.getOutputPath().get());
        } else {
            throw new IllegalArgumentException(String.format(OUTPUT_DESTINATION_ERROR_MSG, parameters.getOutputDestination()));
        }
    }
}