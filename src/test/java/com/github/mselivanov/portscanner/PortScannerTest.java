package com.github.mselivanov.portscanner;

import javax.sound.sampled.Port;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.net.InetAddress;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PortScannerTest {

  @Test
  public void testPortScan() {
    PortScannerParameters parameters = new PortScannerParameters();
    PortScannerEngine engine = mock(PortScannerEngine.class);
    ResultWriter writer = ResultWriterFactory.createResultWriter(parameters);
    PortScanner portScanner = new PortScanner();
    portScanner.scan(engine, parameters);
    Mockito.verify(engine).scan(parameters);
    Mockito.verifyNoMoreInteractions(engine);
  }

  @Test
  public void testWriteResults() {
    PortScannerParameters parameters = new PortScannerParameters();
    PortScannerEngine engine = new PortScannerEngine();
    ResultWriter writer = mock(ResultWriter.class);
    PortScanner portScanner = new PortScanner();
    PortScanResults results = new PortScanResults();
    portScanner.writeResults(results, writer);
    Mockito.verify(writer).writeResults(results);
    Mockito.verifyNoMoreInteractions(writer);
  }

  @Test
  public void testRunScanner() {
    String[] args = {"--host", "www.google.com", "--startport", "80", "--endport", "81"};
    PortScanner scanner = new PortScanner();
    scanner.run(args);
  }

}
