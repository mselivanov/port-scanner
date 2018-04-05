package com.github.mselivanov.portscanner;

import java.net.InetAddress;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PortScanResultTest {
    
    @Test
    void testCreateResult() {
        PortScanResult result = new PortScanResult(InetAddress.getLoopbackAddress(), 1000, PortScanStatus.OPEN);
        assertAll("Test port scan results",
            () -> assertEquals(1000, result.getPort()),
            () -> assertEquals(InetAddress.getLoopbackAddress(), result.getHost()),
            () -> assertEquals(PortScanStatus.OPEN, result.getPortScanStatus()));
    }    
    
}