package com.github.mselivanov.portscanner;

public class PortScanner {

  protected PortScannerEngine engine;
  protected PortScannerParser parser;

  public PortScanner() {
    this(new PortScannerEngine(), new PortScannerParser());
  }

  protected PortScanner(PortScannerEngine engine, PortScannerParser parser) {
    this.engine = engine;
    this.parser = parser;

  }

  /**
   * @param args Command line arguments for running port scanner
   */
  public static void main(String[] args) {
    new PortScanner().run(args);
  }

  /**
   * @param args Command line arguments for running port scanner
   */
  public void run(String[] args) {
    try {
      PortScannerParameters parameters = parseParameters(args);
      ResultWriter writer = createWriter(parameters);
      PortScanResults results = engine.scan(parameters);
      writeResults(results, writer);
    } catch (ParametersParseException e) {
      parser.printHelp();
      System.exit(-1);
    }
  }

  protected void writeResults(final PortScanResults results, final ResultWriter writer) {
    writer.writeResults(results);
  }

  protected PortScannerParameters parseParameters(final String[] args)
      throws ParametersParseException {
    return parser.parse(args);
  }

  protected ResultWriter createWriter(PortScannerParameters parameters) {
    return ResultWriterFactory.createResultWriter(parameters);
  }

}