package com.github.mselivanov.portscanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.net.InetAddress;

public class OutputFormatterTest {
    
    @Test
    public void testDefaultFormatter() {
        OutputFormatter formatter = OutputFormatter.DEFAULT_FORMATTER;
        PortScanResult result = new PortScanResult(InetAddress.getLoopbackAddress(), 1000, PortScanStatus.OPEN);        
        String resultExpected = String.format("Host: %s port %d is %s", result.getHost(), result.getPort(), result.getPortScanStatus());
        String resultActual = formatter.format(result);
        assertEquals(resultExpected, resultActual);
        
    }
}
