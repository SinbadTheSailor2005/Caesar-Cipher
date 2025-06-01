package org.example;

import java.util.ArrayDeque;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {
    ReversePolishNotation rpn = ReversePolishNotation.getInstance();

    System.out.println(rpn.evaluateReversePolishNotationExpression("-5+4*" +
            "(12+3)"));  // 55
    System.out.println(rpn.evaluateReversePolishNotationExpression("2+3*4"));        // 14
    System.out.println(rpn.evaluateReversePolishNotationExpression("1+(2+3)*4"));    // 21
  }

}