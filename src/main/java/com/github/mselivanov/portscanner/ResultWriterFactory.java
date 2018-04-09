package com.github.mselivanov.portscanner;

import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public class ResultWriterFactory {

  private static final String OUTPUT_DESTINATION_ERROR_MSG = "%s destination doesn't supported!";
  private static final String OUTPUT_PATH_NOT_FOUND_ERROR_MSG = "%s path doesn't exist!";

  /**
   * @param parameters Port scanning parameters
   * @return ResultWriter object constructed using provided parameters
   */
  public static ResultWriter createResultWriter(PortScannerParameters parameters) {
    try {
      if (parameters.getOutputDestination() == OutputDestination.CONSOLE) {
        return new ResultWriter(System.out);
      } else if (parameters.getOutputDestination() == OutputDestination.FILE) {
        Path path = parameters.getOutputPath().get();
        PrintStream printStream = createFileStream(path);
        return new ResultWriter(printStream);
      } else {
        throw new IllegalArgumentException(
            String.format(OUTPUT_DESTINATION_ERROR_MSG, parameters.getOutputDestination()));
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(
          String.format(OUTPUT_PATH_NOT_FOUND_ERROR_MSG, parameters.getOutputPath().get()), e);
    }
  }

  protected static PrintStream createFileStream(Path outputPath) throws FileNotFoundException {
    return new PrintStream(fileFromPath(outputPath));
  }

  protected static File fileFromPath(Path path) {
    return path.toFile();
  }
}