# port-scanner
## Disclaimer
This document isn't finished yet.
## Overview
Simple console port scanner written in java. 
Written with a purpose to practice java coding.
Features:
* accepts host IP address or dns name, port range and output destination (file or console);
* scans ports of a given host and outputs results to a given destination.
## Scenarios
### Self-test
```bash
java com.github.mselivanov.portscanner.PortScanner
```

Application uses by default:
* localhost as host name
* 80 as a start port
* 81 as an end port
* console as an output destination

### Scan ports in a particular range of a host and outputs results to file
```bash
java com.github.mselivanov.portscanner.PortScanner --host www.yahoo.com --startport 0 --endport 1023 --file c:/Tmp/ports.txt
```
