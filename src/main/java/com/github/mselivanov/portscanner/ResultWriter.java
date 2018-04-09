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

    /**
     * Constructs ResultWriter object writitng results to @printStream
     * @param printStream Stream to write results to
     */
    public ResultWriter(PrintStream printStream) {
        this.printStream = printStream;
    }

  /**
   * Write results using default output formatter
   * @param results Results to write
   */
  public void writeResults(PortScanResults results) {
        writeResults(results, DEFAULT_FORMATTER);        
    }

  /**
   *
   * @param results Results to write
   * @param formatter Formatter object to format output
   */
  public void writeResults(PortScanResults results, OutputFormatter formatter) {
        for(PortScanResult result: results) {
            printStream.println(formatter.format(result));
        }        
    }

  /**
   *
   * @return Stream ResultWriter writes results to
   */
  public OutputStream getOutputStream() {
        return printStream;
    }

}