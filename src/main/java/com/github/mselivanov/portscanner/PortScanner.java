package com.github.mselivanov.portscanner;

public class PortScanner {
    
    public static void main(String[] args) {
        PortScannerParameters psp;
        PortScannerParser parser = new PortScannerParser();
        PortScannerEngine engine;
        try {
            psp = parser.parse(args);
            psp.setStartPort(80);
            psp.setEndPort(90);
            engine = new PortScannerEngine(psp);
            PortScanResults psr = engine.scan();
            for(PortScanResult result: psr) {
                System.out.println(result.toString());
            }
        } catch(ParametersParseException e) {
            parser.printHelp();
        }
    }
    
}