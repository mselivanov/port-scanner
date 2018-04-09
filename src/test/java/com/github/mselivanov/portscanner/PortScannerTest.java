package com.github.mselivanov.portscanner;

import javax.sound.sampled.Port;
import javax.xml.bind.ParseConversionEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.net.InetAddress;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PortScannerTest extends PortScanner {

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
  public void testRunScanner() throws Exception {
    String[] args = {"--host", "www.google.com", "--startport", "80", "--endport", "81"};
    PortScannerParameters parameters = parseParameters(args);
    PortScanResults results = new PortScanResults();
    ResultWriter writer = ResultWriterFactory.createResultWriter(parameters);
    PortScanner scanner = spy(new PortScanner());
    scanner.engine = mock(PortScannerEngine.class);
    Mockito.doReturn(parameters).when(scanner).parseParameters(args);
    Mockito.doReturn(results).when(scanner.engine).scan(parameters);
    Mockito.doReturn(writer).when(scanner).createWriter(parameters);
    scanner.run(args);
    Mockito.verify(scanner.engine).scan(parameters);
    Mockito.verify(scanner).writeResults(results, writer);
  }

  @Test
  public void testParseParametersSuccessfullParse() throws Exception {
    String[] args = {"--host", "www.google.com", "--startport", "80", "--endport", "81"};
    PortScannerParameters parameters = parseParameters(args);
    assertAll(
        () -> assertEquals("www.google.com", parameters.getHost().getHostName()),
        () -> assertEquals(80, parameters.getStartPort()),
        () -> assertEquals(81, parameters.getEndPort()));
  }

  @Test
  public void testParseParametersErrorOnParse() throws Exception {
    String[] args = {"--host1", "www.google.com"};
    assertThrows(ParametersParseException.class, () -> parseParameters(args));
  }

}
