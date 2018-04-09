package com.github.mselivanov.portscanner;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;


public class PortScanResults implements Iterable<PortScanResult> {
    
    private List<PortScanResult> results;

    /**
     * Constructs container for set of PortScanResult objects
     */
    public PortScanResults() {
        this.results = new ArrayList<>();
    }

    /**
     * Method creates PortScanResult object from params and add it to container
     * @param host Host
     * @param port Port
     * @param portScanStatus Result of scanning @host and @port
     */
    public void addResult(InetAddress host, int port, PortScanStatus portScanStatus) {
        results.add(new PortScanResult(host, port, portScanStatus));
    }

    /**
     * Method adds PortScanResult to container
     * @param result PortScanResult object
     */
    public void addResult(PortScanResult result) {
        results.add(result);
    }

    /**
     *
     * @return Iterator over collection of PortScanResult objects
     */
    public Iterator<PortScanResult> iterator() {
        return results.iterator();
    }
    
}