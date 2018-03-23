package com.github.mselivanov.portscanner;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;


public class PortScanResults implements Iterable<PortScanResult> {
    
    private List<PortScanResult> results;
    
    public PortScanResults() {
        this.results = new ArrayList<>();
    }
    
    public void addResult(InetAddress host, int port, PortScanStatus portScanStatus) {
        results.add(new PortScanResult(host, port, portScanStatus));
    }
    
    public void addResult(PortScanResult result) {
        results.add(result);
    }
    
    public Iterator<PortScanResult> iterator() {
        return results.iterator();
    }
    
}