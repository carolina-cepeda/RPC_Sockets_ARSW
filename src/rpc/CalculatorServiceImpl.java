package rpc;
public class CalculatorServiceImpl implements CalculatorService {

   @Override
   public int add(int a, int b) {
      return a + b;
   }
   @Override
   public int subtract(int a, int b) {
      return a - b;
   }
   @Override
   public int multiply(int a, int b) {
      return a * b;
   }
   @Override
   public int divide(int a, int b) {
      if (b == 0) {
         throw new IllegalArgumentException("Division by zero");
      }
      return a / b;
   }
}