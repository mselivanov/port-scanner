package com.github.mselivanov.portscanner;

public abstract class OutputFormatter {
    
    public static OutputFormatter DEFAULT_FORMATTER = new DefaultOutputFormatter();
    
    private static class DefaultOutputFormatter extends OutputFormatter {
        
        private static final String FORMAT = "Host: %s port %d is %s";
        
        public String format(PortScanResult result) {
            return String.format(FORMAT, result.getHost(), result.getPort(), result.getPortScanStatus());
        }
    }
    
    public abstract String format(PortScanResult result);
}