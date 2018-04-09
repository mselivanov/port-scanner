package com.github.mselivanov.portscanner;

import java.net.InetAddress;

public class PortScanResult {

  private InetAddress host;
  private int port;
  private PortScanStatus portScanStatus;

  /**
   * @param host Host address
   * @param port Port
   * @param portScanStatus Status of scanning @host and @port
   */
  public PortScanResult(InetAddress host, int port, PortScanStatus portScanStatus) {
    this.host = host;
    this.port = port;
    this.portScanStatus = portScanStatus;
  }

  /**
   * @return Host address
   */
  public InetAddress getHost() {
    return host;
  }

  /**
   * @return Port
   */
  public int getPort() {
    return port;
  }

  /**
   * @return Status of scanning host and port
   */
  public PortScanStatus getPortScanStatus() {
    return portScanStatus;
  }

  @Override
  public String toString() {
    return String.format("Host: %s port %d is %s", getHost(), getPort(), getPortScanStatus());
  }
}