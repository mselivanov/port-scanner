package com.github.mselivanov.portscanner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class PortScannerParameters {

  public static final String OUTPUT_FORMAT = "Host: %s\nPort range: %d - %d\nOutput to: %s\n";
  public static final int DEFAULT_START_PORT = 0;
  public static final int DEFAULT_END_PORT = 65535;
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

  public int getStartPort() {
    return startPort;
  }

  public int getEndPort() {
    return endPort;
  }

  public InetAddress getHost() {
    return host;
  }

  public OutputDestination getOutputDestination() {
    return outputDestination;
  }

  public Optional<Path> getOutputPath() {
    return outputPath;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setStartPort(int startPort) {
    this.startPort = startPort;
  }

  public void setEndPort(int endPort) {
    this.endPort = endPort;
  }

  public void setHost(InetAddress host) {
    this.host = host;
  }

  public void setOutputDestination(OutputDestination outputDestination) {
    this.outputDestination = outputDestination;
  }

  public void setOutputPath(Optional<Path> outputPath) {
    this.outputPath = outputPath;
  }

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