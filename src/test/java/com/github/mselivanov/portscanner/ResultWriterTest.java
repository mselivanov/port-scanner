package com.github.mselivanov.portscanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.io.PrintStream;
import java.net.InetAddress;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ResultWriterTest {

  @Test
  public void testResultWriterCreationFromPrintStream() {
    PrintStream mockPrintStream = mock(PrintStream.class);
    ResultWriter writer = new ResultWriter(mockPrintStream);
    assertNotNull(writer);
    assertEquals(mockPrintStream, writer.getOutputStream());
  }

  @Test
  public void testWriteResultsDefaultFormatter() {
    PrintStream printStream = spy(System.out);
    ResultWriter writer = new ResultWriter(printStream);
    assertNotNull(writer);
    PortScanResults results = new PortScanResults();
    PortScanResult result = new PortScanResult(InetAddress.getLoopbackAddress(), 80,
        PortScanStatus.CLOSED);
    results.addResult(result);
    writer.writeResults(results);
    String formattedResult = OutputFormatter.DEFAULT_FORMATTER.format(result);
    Mockito.verify(printStream).println(formattedResult);
  }

  @Test
  public void testCustomOutputFormat() {
    PrintStream printStream = spy(System.out);
    ResultWriter writer = new ResultWriter(printStream);
    assertNotNull(writer);
    PortScanResults results = new PortScanResults();
    PortScanResult result = new PortScanResult(InetAddress.getLoopbackAddress(), 80,
        PortScanStatus.CLOSED);
    results.addResult(result);
    OutputFormatter formatter = new TestOutputFormatter();
    writer.writeResults(results, formatter);
    String formattedResult = formatter.format(result);
    Mockito.verify(printStream).println(formattedResult);
  }

  private static class TestOutputFormatter extends OutputFormatter {

    public static final String FORMAT = "%s-%d-%s";

    @Override
    public String format(PortScanResult result) {
      return String.format(FORMAT, result.getHost().getCanonicalHostName(), result.getPort(),
          result.getPortScanStatus());
    }
  }


}
