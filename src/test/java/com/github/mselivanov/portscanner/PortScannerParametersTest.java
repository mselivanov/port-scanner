package com.github.mselivanov.portscanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

class PortScannerParametersTest {
    
    @Test
    void testDefaultParameters() {
        PortScannerParameters params = new PortScannerParameters();
        assertAll("Default parameters check",
            () -> assertEquals(params.getStartPort(), PortScannerParameters.DEFAULT_START_PORT),
            () -> assertEquals(params.getEndPort(), PortScannerParameters.DEFAULT_END_PORT),
            () -> assertEquals(params.getHost(), PortScannerParameters.DEFAULT_HOST),
            () -> assertEquals(params.getOutputDestination(), PortScannerParameters.DEFAULT_DESTINATION),
            () -> assertFalse(params.getOutputPath().isPresent()),
            () -> assertEquals(params.getTimeout(), PortScannerParameters.DEFAULT_TIMEOUT));
    }
    
    @Test
    void testPortScannerParametersCreation() throws UnknownHostException {
        PortScannerParameters params = new PortScannerParameters();
        params.setStartPort(1);
        params.setEndPort(1023);
        params.setHost(InetAddress.getByName("www.yahoo.com"));
        params.setOutputDestination(OutputDestination.FILE);
        params.setOutputPath(Optional.of(Paths.get("c:", "Tmp", "ports.txt")));
        params.setTimeout(2000);
        
        assertAll("Parameters creation from array",
            () -> assertEquals(1, params.getStartPort()),
            () -> assertEquals(1023, params.getEndPort()),
            () -> assertEquals(InetAddress.getByName("www.yahoo.com"), params.getHost()),
            () -> assertEquals(OutputDestination.FILE, params.getOutputDestination()),
            () -> assertTrue(params.getOutputPath().isPresent()),
            () -> assertEquals(params.getOutputPath().get(), Paths.get("c:", "Tmp", "ports.txt")),
            () -> assertEquals(params.getTimeout(), 2000));
    }
    
}