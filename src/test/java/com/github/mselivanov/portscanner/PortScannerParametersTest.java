package com.github.mselivanov.portscanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;

class PortScannerParametersTest {
    
    @Test
    void testDefaultParameters() {
        PortScannerParameters params = new PortScannerParameters();
        assertAll("Default parameters check",
            () -> assertEquals(params.getStartPort(), 0),
            () -> assertEquals(params.getEndPort(), 65535),
            () -> assertEquals(params.getHost(), InetAddress.getLoopbackAddress()),
            () -> assertEquals(params.getOutputDestination(), OutputDestination.CONSOLE),
            () -> assertFalse(params.getOutputPath().isPresent()));
    }
    
    @Test
    void testPortScannerParametersCreation() throws UnknownHostException, ParametersParseException {
        String[] arrayParams = {"--host", "www.yahoo.com", "--startport", "1", "--endport", "1023", "--file", "c:/Tmp/ports.txt"};
        PortScannerParser parser = new PortScannerParser();
        PortScannerParameters params = parser.parse(arrayParams);
        assertAll("Parameters creation from array",
            () -> assertEquals(1, params.getStartPort()),
            () -> assertEquals(1023, params.getEndPort()),
            () -> assertEquals(InetAddress.getByName("www.yahoo.com"), params.getHost()),
            () -> assertEquals(OutputDestination.FILE, params.getOutputDestination()),
            () -> assertTrue(params.getOutputPath().isPresent()),
            () -> assertEquals(params.getOutputPath().get(), Paths.get("c:", "Tmp", "ports.txt")));
    }
    
}