package com.github.mselivanov.portscanner;

import java.net.InetAddress;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PortScanResultsTest {
    
    @Test
    void testAddResultFromPortScanResult() {
        PortScanResults results = new PortScanResults();
        PortScanResult result = new PortScanResult(InetAddress.getLoopbackAddress(), 1000, PortScanStatus.OPEN);
        results.addResult(result);
        for(PortScanResult r: results) {
        assertAll("Test port scan results",
            () -> assertEquals(1000, r.getPort()),
            () -> assertEquals(InetAddress.getLoopbackAddress(), r.getHost()),
            () -> assertEquals(PortScanStatus.OPEN, r.getPortScanStatus()));
        }
    }
    
    @Test
    void testAddResultFromParameters() {
        PortScanResults results = new PortScanResults();
        PortScanResult result = new PortScanResult(InetAddress.getLoopbackAddress(), 1000, PortScanStatus.OPEN);
        results.addResult(InetAddress.getLoopbackAddress(), 1000, PortScanStatus.OPEN);
        for(PortScanResult r: results) {
        assertAll("Test port scan results",
            () -> assertEquals(1000, r.getPort()),
            () -> assertEquals(InetAddress.getLoopbackAddress(), r.getHost()),
            () -> assertEquals(PortScanStatus.OPEN, r.getPortScanStatus()));
        }
    }
    
}