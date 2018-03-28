package com.github.mselivanov.portscanner;

public abstract class ResultWriter {
    public abstract void writeResults(PortScanResults results);
    public abstract void writeResults(PortScanResults results, OutputFormatter formatter);
}