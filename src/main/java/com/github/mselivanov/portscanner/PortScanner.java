package com.github.mselivanov.portscanner;

public class PortScanner {

  public static void main(String[] args) {
    PortScanner scanner = new PortScanner();
    scanner.run(args);
  }

  public void run(String[] args) {
    PortScannerEngine engine = new PortScannerEngine();
    PortScannerParser parser = new PortScannerParser();
    PortScannerParameters parameters;
    try {
      parameters = parser.parse(args);
      ResultWriter writer = ResultWriterFactory.createResultWriter(parameters);
      PortScanResults results = scan(engine, parameters);
      writeResults(results, writer);
    } catch (ParametersParseException e) {
      parser.printHelp();
      System.exit(-1);
    }
  }

  public PortScanResults scan(PortScannerEngine engine, PortScannerParameters parameters) {
    return engine.scan(parameters);
  }

  public void writeResults(PortScanResults results, ResultWriter writer) {
    writer.writeResults(results);
  }

}