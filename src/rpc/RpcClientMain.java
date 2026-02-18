package rpc;

import java.util.Scanner;

public class RpcClientMain {
      public static void main(String[] args) {
            CalculatorService calc = new CalculatorClientStub("127.0.0.1", 5000);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                  System.out.print("Enter first number: ");
                  int a = scanner.nextInt();
                  System.out.print("Enter second number: ");
                  int b = scanner.nextInt();
                  System.out.print("Enter operation (+, -, *, /): ");
                  String op = scanner.next();
                  int result = 0;
                  switch (op) {
                        case "+":
                              result = calc.add(a, b);
                              break;
                        case "-":
                              result = calc.subtract(a, b);
                              break;
                        case "*":
                              result = calc.multiply(a, b);
                              break;
                        case "/":
                              result = calc.divide(a, b);
                              break;
                        default:
                              System.out.println("Invalid operation");
                              continue;
                  }
                  System.out.println("Result: " + result);
            }
      }
}
