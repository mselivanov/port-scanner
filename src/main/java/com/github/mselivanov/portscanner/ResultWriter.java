package com.github.mselivanov.portscanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static com.github.mselivanov.portscanner.OutputFormatter.*;
import static java.util.Optional.of;

public class ResultWriter {
    
    private PrintStream printStream;
    private Optional<Path> outputPath = Optional.empty();
    
    public ResultWriter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public ResultWriter(Path outputPath) throws FileNotFoundException {
      this.printStream = new PrintStream(outputPath.toFile());
      this.outputPath = Optional.of(outputPath);
    }
    
    public void writeResults(PortScanResults results) {
        writeResults(results, DEFAULT_FORMATTER);        
    }
    
    public void writeResults(PortScanResults results, OutputFormatter formatter) {
        for(PortScanResult result: results) {
            printStream.println(formatter.format(result));
        }        
    }

    public OutputStream getOutputStream() {
        return printStream;
    }

    public Optional<Path> getOutputPath() {
      return outputPath;
    }

}