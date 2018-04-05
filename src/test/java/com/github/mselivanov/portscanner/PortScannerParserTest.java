package com.github.mselivanov.portscanner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;


class PortScannerParserTest {
    
    @Test
    void testLongParameters() throws UnknownHostException, ParametersParseException {
        String[] arrayParams = {"--host", "www.yahoo.com", "--startport", "1", "--endport", "1023", "--file", "c:/Tmp/ports.txt", "--timeout", "1000"};
        PortScannerParser parser = new PortScannerParser();
        PortScannerParameters params = parser.parse(arrayParams);
        assertAll("Parameters creation from array (long parameters)",
            () -> assertEquals(1, params.getStartPort()),
            () -> assertEquals(1023, params.getEndPort()),
            () -> assertEquals(InetAddress.getByName("www.yahoo.com"), params.getHost()),
            () -> assertEquals(OutputDestination.FILE, params.getOutputDestination()),
            () -> assertTrue(params.getOutputPath().isPresent()),
            () -> assertEquals(params.getOutputPath().get(), Paths.get("c:", "Tmp", "ports.txt")),
            () -> assertEquals(params.getTimeout(), 1000));
    }

    @Test
    void testShortParameters() throws UnknownHostException, ParametersParseException {
        String[] arrayParams = {"-h", "www.yahoo.com", "-sp", "1", "-ep", "1023", "-f", "c:/Tmp/ports.txt", "--timeout", "1000"};
        PortScannerParser parser = new PortScannerParser();
        PortScannerParameters params = parser.parse(arrayParams);
        assertAll("Parameters creation from array (long parameters)",
            () -> assertEquals(1, params.getStartPort()),
            () -> assertEquals(1023, params.getEndPort()),
            () -> assertEquals(InetAddress.getByName("www.yahoo.com"), params.getHost()),
            () -> assertEquals(OutputDestination.FILE, params.getOutputDestination()),
            () -> assertTrue(params.getOutputPath().isPresent()),
            () -> assertEquals(params.getOutputPath().get(), Paths.get("c:", "Tmp", "ports.txt")),
            () -> assertEquals(params.getTimeout(), 1000));
    }

    @Test
    void testShortAndLongParametersMixed() throws UnknownHostException, ParametersParseException {
        String[] arrayParams = {"--host", "www.yahoo.com", "-sp", "1", "-ep", "1023", "--file", "c:/Tmp/ports.txt", "--timeout", "1000"};
        PortScannerParser parser = new PortScannerParser();
        PortScannerParameters params = parser.parse(arrayParams);
        assertAll("Parameters creation from array (long parameters)",
            () -> assertEquals(1, params.getStartPort()),
            () -> assertEquals(1023, params.getEndPort()),
            () -> assertEquals(InetAddress.getByName("www.yahoo.com"), params.getHost()),
            () -> assertEquals(OutputDestination.FILE, params.getOutputDestination()),
            () -> assertTrue(params.getOutputPath().isPresent()),
            () -> assertEquals(params.getOutputPath().get(), Paths.get("c:", "Tmp", "ports.txt")),
            () -> assertEquals(params.getTimeout(), 1000));
    }
    
    @Test
    void testExceptionOnUnknownParameter() throws UnknownHostException, ParametersParseException {
        String[] arrayParams = {"-h", "www.yahoo.com", "--email", "mail@mail.com"};
        PortScannerParser parser = new PortScannerParser();        
        assertAll("Unknown parameter",
            () -> assertThrows(ParametersParseException.class, () -> parser.parse(arrayParams)));
    }
    
    @Test
    void testExceptionOnUnknownHost() {
        String[] arrayParams = {"-h", "www.23qwertyboofoo.wada"};
        PortScannerParser parser = new PortScannerParser();
        try {
            parser.parse(arrayParams);
            assertFalse(1 == 1, "Must throw an exception in previous line");
        } catch(ParametersParseException e) {            
            assertAll("Unknow host",
                () -> assertTrue(e.getCause() instanceof UnknownHostException));
        }
    }
    
    
}