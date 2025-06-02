package org.example;

import org.example.ArithmeticEvalAlgo.ReversePolishNotation;
import org.example.ArithmeticEvalAlgo.ReversePolishNotationInteractor;
import org.example.CaesarCipher.CaesarCipher;
import org.example.CaesarCipher.CaesarInteractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
  public static void main(String[] args) throws IOException {
    CaesarInteractor caesarInteractor = CaesarInteractor.getInstance();
    ReversePolishNotationInteractor reversePolishNotationInteractor =
            ReversePolishNotationInteractor.getInstance();
                    BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      System.out.println("""
                         
                         Welcome to Gehtsoft Technical Assessment
                         Please choose an option (type the number):
                         1. Caesar Cipher Algorithm
                         2. Arithmetic Expression Evaluation
                         3. Exit
                         
                         """);
      String option = reader.readLine()
              .strip();
      switch (option) {
        case "1":
          System.out.println("Caesar Cipher Algorithm was chosen");
          caesarInteractor.interact();
          break;
        case "2":
          System.out.println("""
                             Arithmetic Expression Evaluation was chosen.
                             
                             
                             """);
          reversePolishNotationInteractor.interact();
          break;
        case "3":
          return;
      }
    }

  }
}