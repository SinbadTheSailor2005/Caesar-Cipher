package org.example.ArithmeticEvalAlgo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ReversePolishNotation {
  boolean expectUnary = true;
  public static ReversePolishNotation reversePolishNotationInstance =
          new ReversePolishNotation();

  private ReversePolishNotation() {
  }

  public static ReversePolishNotation getInstance() {
    return reversePolishNotationInstance;
  }


  private Deque<String> convertToReversePolishNotation(String expression) {
    Stack<String> operands = new Stack<>();
    ArrayDeque<String> output = new ArrayDeque<>();
    expectUnary = true;

    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);

      if (Character.isWhitespace(c)) continue;

      if (Character.isDigit(c)) {
        StringBuilder number = new StringBuilder();
        while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
          number.append(expression.charAt(i++));
        }
        output.push(number.toString());
        i--;
        expectUnary = false;
        continue;
      }

      if (c == '(') {
        operands.push(String.valueOf(c));
        expectUnary = true;
        continue;
      }

      if (c == ')') {
        while (!operands.isEmpty() && !operands.peek().equals("(")) {
          output.push(operands.pop());
        }
        if (operands.isEmpty() || !operands.peek().equals("(")) {
          throw new RuntimeException("Invalid input: mismatched parentheses");
        }
        operands.pop();
        expectUnary = false;
        continue;
      }

      if (isOperator(c)) {
        String currentOp = String.valueOf(c);
        if (c == '-' && expectUnary) {
          output.push("0");
        }

        while (!operands.isEmpty() && isOperator(operands.peek().charAt(0))
                && precedence(operands.peek().charAt(0)) >= precedence(c)) {
          output.push(operands.pop());
        }
        operands.push(currentOp);
        expectUnary = true;
        continue;
      }

      throw new RuntimeException("Invalid character: '" + c + "'");
    }

    while (!operands.isEmpty()) {
      String oper = operands.pop();
      if (!isOperator(oper.charAt(0))) {
        throw new RuntimeException("Invalid input: mismatched parentheses");
      }
      output.push(oper);
    }

    return output.reversed();
  }

  private boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/';
  }

  private int precedence(char op) {
    return (op == '+' || op == '-') ? 1 : 2;
  }

  public int evaluateExpression(String expression) {
    Deque<String> rpn = convertToReversePolishNotation(expression);
    Stack<Integer> stack = new Stack<>();

    for (String token : rpn) {
      if (isOperator(token.charAt(0))) {
        if (stack.size() < 2) {
          throw new RuntimeException("Not enough operands for " + token);
        }
        int b = stack.pop();
        int a = stack.pop();
        switch (token.charAt(0)) {
          case '+': stack.push(a + b); break;
          case '-': stack.push(a - b); break;
          case '*': stack.push(a * b); break;
          case '/':
            if (b == 0) throw new ArithmeticException("Division by 0");
            stack.push(a / b); break;
        }
      } else {

          stack.push(Integer.parseInt(token));

      }
    }

    if (stack.size() != 1) {
      throw new RuntimeException("Invalid expression");
    }

    return stack.pop();
  }
}
