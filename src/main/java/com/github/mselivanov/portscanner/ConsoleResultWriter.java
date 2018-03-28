package com.github.mselivanov.portscanner;

import static com.github.mselivanov.portscanner.OutputFormatter.*;

public class ConsoleResultWriter extends ResultWriter {
    
    public void writeResults(PortScanResults results) {
        writeResults(results, DEFAULT_FORMATTER);
    }
    
    public void writeResults(PortScanResults results, OutputFormatter formatter) {
        for(PortScanResult result: results) {
            System.out.println(formatter.format(result));
        }
    }

}