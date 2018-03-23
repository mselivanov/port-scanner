package com.github.mselivanov.portscanner;

import java.net.InetAddress;

public class PortScanResult {
    
    private InetAddress host;
    private int port;
    private PortScanStatus portScanStatus;
    
    public PortScanResult(InetAddress host, int port, PortScanStatus portScanStatus){
        this.host = host;
        this.port = port;
        this.portScanStatus = portScanStatus;
    }
    
    public InetAddress getHost() {
        return host;
    }
    
    public int getPort() {
        return port;
    }
    
    public PortScanStatus getPortScanStatus() {
        return portScanStatus;
    }
}