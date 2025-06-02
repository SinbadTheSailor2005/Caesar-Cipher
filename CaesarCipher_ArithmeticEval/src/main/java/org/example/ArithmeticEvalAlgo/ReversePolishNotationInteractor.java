package org.example.ArithmeticEvalAlgo;

import org.example.CaesarCipher.Interactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReversePolishNotationInteractor implements Interactor {
  private static final ReversePolishNotationInteractor interactorInstance =
          new ReversePolishNotationInteractor();
  private static final ReversePolishNotation polishNotation =
          ReversePolishNotation.getInstance();

  private ReversePolishNotationInteractor() {
  }

  public static ReversePolishNotationInteractor getInstance() {
    return interactorInstance;
  }

  @Override
  public void interact() throws IOException {
    BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
    System.out.println(
            """
            Please, enter the expression
            """);
    String expression = reader.readLine()
            .strip();
    double res = polishNotation.evaluateExpression(expression);
    System.out.println("Answer: " + res);
  }
}
