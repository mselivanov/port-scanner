package com.github.mselivanov.portscanner;

public enum PortScanStatus {
    UNKNOWN("unknown"),
    OPEN("open"),
    CLOSED("closed");
    
    private String text;
    
    PortScanStatus(String text) {
        this.text = text;
    }
    
    @Override
    public String toString(){
        return text;
    }
}