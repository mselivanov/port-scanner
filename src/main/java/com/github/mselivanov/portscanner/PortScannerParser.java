package com.github.mselivanov.portscanner;

import static com.github.mselivanov.portscanner.PortScannerParameters.*;
import java.net.UnknownHostException;
import java.util.Optional;
import java.nio.file.Paths;
import java.net.InetAddress;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

public class PortScannerParser {

    private static final String USAGE_SYNTAX = "java PortScanner [--host <host name or IP> ] [--ports <startPort-endPort>] [--file <path to file to store results>]";
    
    private static Options createOptions() {
        Options options = new Options();
        Option host = new Option("h", "host", true, "Host");
        host.setRequired(false);
        options.addOption(host);
        
        Option startPort = new Option("sp", "startport", true, "Start port to scan");
        startPort.setRequired(false);
        options.addOption(startPort);

        Option endPort = new Option("ep", "endport", true, "End port to scan");
        endPort.setRequired(false);
        options.addOption(endPort);

        Option file = new Option("f", "file", true, "File to output result of port scanning");
        file.setRequired(false);
        options.addOption(file);
        
        return options;
    } 
    
    private Options options;
    
    public PortScannerParser() {
        options = createOptions();
    }
    
    public PortScannerParameters parse(String[] args) throws ParametersParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd; 
        PortScannerParameters portScannerParameters = new PortScannerParameters();
        try {
            cmd = parser.parse(options, args);
            portScannerParameters.setStartPort(Integer.parseInt(cmd.getOptionValue("sp", "" + DEFAULT_START_PORT)));
            portScannerParameters.setEndPort(Integer.parseInt(cmd.getOptionValue("ep", "" + DEFAULT_END_PORT)));
            portScannerParameters.setHost(cmd.hasOption("h") ? InetAddress.getByName(cmd.getOptionValue("h")): DEFAULT_HOST);
            portScannerParameters.setOutputDestination(cmd.hasOption("f") ? OutputDestination.FILE : DEFAULT_DESTINATION);
            portScannerParameters.setOutputPath(cmd.hasOption("f") ? Optional.of(Paths.get(cmd.getOptionValue("f"))) : DEFAULT_FILEPATH);            
        } catch(ParseException e) {
            throw new ParametersParseException(e);
        } catch(UnknownHostException e) {
            throw new ParametersParseException(e);            
        }
        return portScannerParameters;
    }
    
    public void printHelp() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(USAGE_SYNTAX, options);        
    } 
    
}