package com.github.mselivanov.portscanner;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.IOException;

public class PortScannerEngine {

  /**
   *
   * @param parameters Parameters for port scan
   * @return PortScanResults object containing collection of PortScanResult objects
   */
  public PortScanResults scan(PortScannerParameters parameters) {
    int startPort = parameters.getStartPort();
    int endPort = parameters.getEndPort();
    PortScanResults psr = new PortScanResults();
    for (int port = startPort; port <= endPort; port++) {
      try (Socket socket = new Socket()) {
        socket.connect(new InetSocketAddress(parameters.getHost(), port), 500);
        psr.addResult(parameters.getHost(), port, PortScanStatus.OPEN);
	socket.close();
      } catch (IOException e) {
        psr.addResult(parameters.getHost(), port, PortScanStatus.CLOSED);
      }

    }
    return psr;
  }
}
