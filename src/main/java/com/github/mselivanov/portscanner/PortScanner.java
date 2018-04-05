package com.github.mselivanov.portscanner;

public class PortScanner {

  private PortScannerEngine engine;
  private PortScannerParameters parameters;
  private ResultWriter writer;

  public PortScanner(PortScannerParameters parameters) {
    this.engine = new PortScannerEngine();
    this.parameters = parameters;
    this.writer = ResultWriterFactory.createResultWriter(parameters);
  }

  public static void main(String[] args) {
    PortScannerParser parser = new PortScannerParser();
    PortScannerParameters parameters;
    try {
      parameters = parser.parse(args);
      PortScanner portScanner = new PortScanner(parameters);
      PortScanResults results = portScanner.scan();
      portScanner.writeResults(results);
    } catch (ParametersParseException e) {
      parser.printHelp();
      System.exit(-1);
    }
  }

  public PortScanResults scan() {
    return engine.scan(parameters);
  }

  public void writeResults(PortScanResults results) {
    writer.writeResults(results);
  }
}