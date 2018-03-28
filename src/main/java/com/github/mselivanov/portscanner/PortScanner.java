package com.github.mselivanov.portscanner;

public class PortScanner {
    
    public static void main(String[] args) {
        PortScannerParameters psp;
        PortScannerParser parser = new PortScannerParser();
        PortScannerEngine engine;
        OutputFormatter formatter = OutputFormatter.DEFAULT_FORMATTER;
        ResultWriter writer;
        try {
            psp = parser.parse(args);
            engine = new PortScannerEngine(psp);
            PortScanResults psr = engine.scan();
            writer = ResultWriterFactory.createResultWriter(psp);
            writer.writeResults(psr);            
        } catch(ParametersParseException e) {
            parser.printHelp();
        }
    }
    
}