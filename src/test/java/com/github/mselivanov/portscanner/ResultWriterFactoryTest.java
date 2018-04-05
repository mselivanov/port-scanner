package com.github.mselivanov.portscanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class ResultWriterFactoryTest {

  @Test
  public void testConsoleOutputCreation() {
    PortScannerParameters parameters = new PortScannerParameters();
    ResultWriter writer = ResultWriterFactory.createResultWriter(parameters);
    assertTrue(writer.getOutputStream() == System.out);
  }

  @Test
  public void testFileOutputCreation() {
    PortScannerParameters parameters = new PortScannerParameters();
    parameters.setOutputDestination(OutputDestination.FILE);
    File f = new File(
        getClass().getClassLoader().getResource("dummy.txt").getFile());
    parameters.setOutputPath(Optional.of(Paths.get(f.getAbsolutePath())));
    ResultWriter writer = ResultWriterFactory.createResultWriter(parameters);
    assertTrue(writer.getOutputPath().isPresent());
  }


}
