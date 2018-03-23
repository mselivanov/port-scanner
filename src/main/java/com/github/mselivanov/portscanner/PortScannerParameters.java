package com.github.mselivanov.portscanner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;


public class PortScannerParameters {
    
    private static final String OUTPUT_FORMAT = "Host: %s\nPort range: %d - %d\nOutput to: %s\n";
    private static final String USAGE_SYNTAX = "java PortScanner [--host <host name or IP> ] [--ports <startPort-endPort>] [--file <path to file to store results>]";
    private static final int DEFAULT_START_PORT = 0;
    private static final int DEFAULT_END_PORT = 65535;
    private static final InetAddress DEFAULT_HOST = InetAddress.getLoopbackAddress();
    private static final OutputDestination DEFAULT_DESTINATION = OutputDestination.CONSOLE;
    private static final Optional<Path> DEFAULT_FILEPATH = Optional.empty();
    
    private InetAddress host = DEFAULT_HOST;
    private int startPort = DEFAULT_START_PORT;
    private int endPort = DEFAULT_END_PORT;    
    private OutputDestination outputDestination = DEFAULT_DESTINATION;
    private Optional<Path> outputPath = DEFAULT_FILEPATH;
    
    // Allow creation only by using factory method
    private PortScannerParameters() {}
    
    private static Options buildOptions() {
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
    
    // TODO: Move help print to PrintScanner class
    public static final PortScannerParameters fromParametersArray(String[] params) throws ParametersParseException {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();
        CommandLine cmd; 
        Options options = buildOptions();
        PortScannerParameters portScannerParameters = new PortScannerParameters();
        try {
            cmd = parser.parse(options, params);
            portScannerParameters.startPort = Integer.parseInt(cmd.getOptionValue("sp", "" + DEFAULT_START_PORT));
            portScannerParameters.endPort = Integer.parseInt(cmd.getOptionValue("ep", "" + DEFAULT_END_PORT));
            portScannerParameters.host = cmd.hasOption("h") ? InetAddress.getByName(cmd.getOptionValue("h")): DEFAULT_HOST;
            portScannerParameters.outputDestination = cmd.hasOption("f") ? OutputDestination.FILE : DEFAULT_DESTINATION;
            portScannerParameters.outputPath = cmd.hasOption("f") ? Optional.of(Paths.get(cmd.getOptionValue("f"))) : DEFAULT_FILEPATH;            
        } catch(ParseException e) {
            helpFormatter.printHelp(USAGE_SYNTAX, options);
            throw new ParametersParseException(e);
        } catch(UnknownHostException e) {
            helpFormatter.printHelp(USAGE_SYNTAX, options);
            throw new ParametersParseException(e);            
        }
        return portScannerParameters;
    }

    public static final PortScannerParameters defaultPortScannerParameters() {        
        return new PortScannerParameters();
    }
    
    public int getStartPort() {
        return startPort;
    }
    
    public int getEndPort() {
        return endPort;
    }
    
    public InetAddress getHost() {
        return host;
    }
    
    public OutputDestination getOutputDestination() {
        return outputDestination;
    }
    
    public Optional<Path> getOutputPath() {
        return outputPath;
    }    
    
    @Override
    public String toString() {
        String output = outputDestination.toString();
        if (outputDestination == OutputDestination.FILE && outputPath.isPresent()) {
            output += outputPath.get().toString();
        }
        return String.format(OUTPUT_FORMAT, host.getHostAddress(), startPort, endPort, output);
    }
}