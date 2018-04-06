package com.github.mselivanov.portscanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Path;
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
    assertNotNull(writer);
  }

  @Test
  public void testOutputFilePath() {
    File expectedFile = new File(
        getClass().getClassLoader().getResource("dummy.txt").getFile());
    Path path = Paths.get(expectedFile.getAbsolutePath());
    File actualFile = TestResultWriterFactory.fileFromPath(path);
    assertEquals(expectedFile, actualFile);
  }

  @Test
  public void testFileOutputStreamCreation() throws Exception{
    File expectedFile = new File(
        getClass().getClassLoader().getResource("dummy.txt").getFile());
    Path path = Paths.get(expectedFile.getAbsolutePath());
    PrintStream printStream = TestResultWriterFactory.createFileStream(path);
    assertNotNull(printStream);
  }


  @Test
  public void testFileNotExists() {
    PortScannerParameters parameters = new PortScannerParameters();
    parameters.setOutputDestination(OutputDestination.FILE);
    parameters.setOutputPath(Optional.of(Paths.get("file///Buhaha/unknown path")));
    assertThrows(IllegalArgumentException.class,
        () -> ResultWriterFactory.createResultWriter(parameters));
  }

  @Test
  public void testUnknownOutputDestination() {
    PortScannerParameters parameters = new PortScannerParameters();
    parameters.setOutputDestination(OutputDestination.UNKNOWN);
    assertThrows(IllegalArgumentException.class,
        () -> ResultWriterFactory.createResultWriter(parameters));
  }

  private static class TestResultWriterFactory extends ResultWriterFactory {

    public File fileFromPathWrapper(Path path) {
      return ResultWriterFactory.fileFromPath(path);
    }

    public PrintStream createFileStreamWrapper(Path outputPath) throws FileNotFoundException{
      return ResultWriterFactory.createFileStream(outputPath);
    }
  }

}
