package com.github.mselivanov.portscanner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class PortScannerParameters {

  public static final String OUTPUT_FORMAT = "Host: %s\nPort range: %d - %d\nOutput to: %s\n";
  public static final int DEFAULT_START_PORT = 80;
  public static final int DEFAULT_END_PORT = 81;
  public static final InetAddress DEFAULT_HOST = InetAddress.getLoopbackAddress();
  public static final OutputDestination DEFAULT_DESTINATION = OutputDestination.CONSOLE;
  public static final Optional<Path> DEFAULT_FILEPATH = Optional.empty();
  public static final int DEFAULT_TIMEOUT = 500;

  private InetAddress host = DEFAULT_HOST;
  private int startPort = DEFAULT_START_PORT;
  private int endPort = DEFAULT_END_PORT;
  private OutputDestination outputDestination = DEFAULT_DESTINATION;
  private Optional<Path> outputPath = DEFAULT_FILEPATH;
  private int timeout = DEFAULT_TIMEOUT;

  public PortScannerParameters() {
  }

  /**
   *
   * @param host
   * @param startPort Start port for scanning
   * @param endPort End port for scanning. Port is included in scanning.
   * @param outputDestination Output destination.
   * @param outputPath Path to output file if Output destination is file.
   * @param timeout Timeout for establishing connection to a given host and port
   */
  public PortScannerParameters(InetAddress host, int startPort, int endPort,
      OutputDestination outputDestination, Optional<Path> outputPath, int timeout) {
    setHost(host);
    setStartPort(startPort);
    setEndPort(endPort);
    setOutputDestination(outputDestination);
    setOutputPath(outputPath);
  }

  /**
   * Copying constructor.
   *
   * @param parameters object to copy from
   */
  public PortScannerParameters(PortScannerParameters parameters) {
    try {
      this.host = InetAddress.getByAddress(parameters.getHost().getAddress());
    } catch (UnknownHostException e) {
      this.host = InetAddress.getLoopbackAddress();
    }
    this.startPort = parameters.getStartPort();
    this.endPort = parameters.getEndPort();
    this.outputDestination = parameters.getOutputDestination();
    this.outputPath = parameters.getOutputPath().isPresent() ? Optional
        .of(Paths.get(parameters.getOutputPath().get().toUri())) : Optional.empty();
  }

  /**
   *
   * @return Host address
   */
  public InetAddress getHost() {
    return host;
  }

  /**
   *
   * @param host Sets host address
   */
  public void setHost(InetAddress host) {
    this.host = host;
  }

  /**
   *
   * @return Start port for scan
   */
  public int getStartPort() {
    return startPort;
  }

  /**
   *
   * @param startPort Sets start port for scan
   */
  public void setStartPort(int startPort) {
    this.startPort = startPort;
  }

  /**
   *
   * @return End port for scan
   */
  public int getEndPort() {
    return endPort;
  }

  /**
   *
   * @param endPort Sets end port for scan
   */
  public void setEndPort(int endPort) {
    this.endPort = endPort;
  }

  /**
   *
   * @return Output destination
   */
  public OutputDestination getOutputDestination() {
    return outputDestination;
  }

  /**
   *
   * @param outputDestination Sets output destination
   */
  public void setOutputDestination(OutputDestination outputDestination) {
    this.outputDestination = outputDestination;
  }

  /**
   *
   * @return Optional of output path. Optional.empty() - if path is not set.
   */
  public Optional<Path> getOutputPath() {
    return outputPath;
  }

  /**
   *
   * @param outputPath Set output path
   */
  public void setOutputPath(Optional<Path> outputPath) {
    this.outputPath = outputPath;
  }

  /**
   *
   * @return Timeout in milliseconds for establishing connection
   */
  public int getTimeout() {
    return timeout;
  }

  /**
   *
   * @param timeout Set timeout for establishing connection
   */
  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  @Override
  public String toString() {
    String output = outputDestination.toString();
    if (outputDestination == OutputDestination.FILE && outputPath.isPresent()) {
      output += outputPath.get().toString();
    }
    return String.format(OUTPUT_FORMAT, host.getHostAddress(), startPort, endPort, output);
  }
}
