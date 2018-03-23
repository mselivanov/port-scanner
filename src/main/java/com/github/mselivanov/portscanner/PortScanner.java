package com.github.mselivanov.portscanner;

public class PortScanner {
    
    public static void main(String[] args) {
        PortScanner portScanner = new PortScanner();
        PortScannerParameters psp;
        try {
            psp = PortScannerParameters.fromParametersArray(null);
        } catch(ParametersParseException e) {
            // Help is printed in parameters class
        }
    }
    
}