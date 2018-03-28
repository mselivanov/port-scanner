package com.github.mselivanov.portscanner;

import static com.github.mselivanov.portscanner.OutputFormatter.*;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileResultWriter extends ResultWriter {
    
    private Path outputPath;
    
    public FileResultWriter(Path outputPath) {
        this.outputPath = outputPath;
    }
    
    public void writeResults(PortScanResults results) {
        writeResults(results, OutputFormatter.DEFAULT_FORMATTER);
    }
    
    public void writeResults(PortScanResults results, OutputFormatter formatter) {
        try(BufferedWriter writer = Files.newBufferedWriter(outputPath, Charset.defaultCharset())) {
            for(PortScanResult result: results) {
                writer.write(formatter.format(result));
                writer.newLine();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}