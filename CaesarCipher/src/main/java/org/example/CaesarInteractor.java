package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CaesarInteractor implements Interactor {


  private static final CaesarCipher caesarCipher = CaesarCipher.getInstance();
  private static final CaesarInteractor CaesarInteractorInstance =
          new CaesarInteractor();

  public static CaesarInteractor getInstance() {
    return CaesarInteractorInstance;
  }

  private CaesarInteractor() {
  }

  @Override
  public void interact() throws IOException {
    System.out.println("Select input source. Type <file> or <stdin>");
    BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
    String sourceType = reader.readLine()
            .strip()
            .toLowerCase();

    List<String> inputLines;
    switch (sourceType) {
      case "file" -> inputLines = readFromFile(reader);
      case "stdin" -> inputLines = readFromStdin(reader);
      default ->
              throw new RuntimeException("Invalid input source: " + sourceType);
    }

    processInput(inputLines);
  }

  private List<String> readFromFile(BufferedReader reader) throws IOException {
    System.out.println("""
                       The file was chosen as input.
                       Please, type ABSOLUTE path to the file:
                       """);

    Path filepath = Paths.get(reader.readLine()
            .strip());
    if (!Files.exists(filepath)) {
      throw new RuntimeException("File does not exist: " + filepath);
    }

    List<String> lines = Files.readAllLines(filepath)
            .stream()
            .filter(line -> !line.isBlank())
            .toList();

    if (lines.size() < 2) {
      throw new RuntimeException(
              "File must contain at least two lines: mode and message");
    }

    return lines;
  }

  private List<String> readFromStdin(BufferedReader reader) throws IOException {
    List<String> lines = new ArrayList<>();
    System.out.println("Enter mode (encryption/decryption):");
    lines.add(reader.readLine()
            .strip());

    System.out.println("Enter message:");
    lines.add(reader.readLine()
            .strip());

    System.out.println("Enter shift (optional for decryption - put just " +
            "enter)" +
            " :");
    String shiftLine = reader.readLine()
            .strip();
    if (!shiftLine.isBlank()) {
      lines.add(shiftLine);
    }

    return lines;
  }

  private void processInput(List<String> input) {
    String mode = input.get(0)
            .toLowerCase();
    String message = input.get(1);
    int shift;

    if (!(mode.equals("encryption") || mode.equals("decryption"))) {
      throw new RuntimeException("Invalid mode: " + mode);
    }
    if (message.isBlank()) {
      throw new RuntimeException("Message is empty");
    }

    if (input.size() > 2) {
      try {
        shift = Integer.parseInt(input.get(2));
      } catch (NumberFormatException e) {
        throw new RuntimeException("Invalid shift value: " + input.get(2));
      }
    } else {
      if (mode.equals("encryption")) {
        throw new RuntimeException("No shift value to encrypt message");
      }
      shift = caesarCipher.calculateShift(message);
    }

    String result = mode.equals("encryption")
            ? caesarCipher.shift(shift, message)
            : caesarCipher.shift(-shift, message);

    System.out.println("Result:");
    System.out.println(result);
  }


}
