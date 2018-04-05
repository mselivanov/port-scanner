package com.github.mselivanov.portscanner;

import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.net.InetAddress;

public class PortScannerEngineTest {

  @Test
  public void testPortScanPortOpened() throws UnknownHostException {
    PortScannerParameters parameters = new PortScannerParameters();
    parameters.setHost(InetAddress.getByName("www.google.com"));
    parameters.setStartPort(80);
    parameters.setEndPort(80);
    PortScannerEngine engine = new PortScannerEngine();
    PortScanResults results = engine.scan(parameters);
    for(PortScanResult result: results) {
      assertEquals(result.getPortScanStatus(), PortScanStatus.OPEN);
    }
  }

  @Test
  public void testPortScanPortClosed() throws UnknownHostException {
    PortScannerParameters parameters = new PortScannerParameters();
    parameters.setHost(InetAddress.getByName("www.google.com"));
    parameters.setStartPort(81);
    parameters.setEndPort(81);
    PortScannerEngine engine = new PortScannerEngine();
    PortScanResults results = engine.scan(parameters);
    for(PortScanResult result: results) {
      assertEquals(result.getPortScanStatus(), PortScanStatus.CLOSED);
    }
  }

}
