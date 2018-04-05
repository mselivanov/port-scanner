package com.github.mselivanov.portscanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.net.InetAddress;

public class PortScannerTest {

  @Test
  public void testPortScannerCreationSuccess() {
    String[] args = {"--startport", "80", "--endport", "82", "--host", "www.google.com", "--file",
        "c:/Tmp/output.txt"};
    PortScannerParameters parameters = new PortScannerParameters();
    PortScanner portScanner = new PortScanner(parameters);
  }
}
