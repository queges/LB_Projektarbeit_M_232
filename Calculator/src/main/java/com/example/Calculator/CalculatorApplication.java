package com.example.Calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorApplication {
  public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Operationen als Map mit Lambda-Ausdrücken
        Map<Integer, BiFunction<Double, Double, Double>> operations = Map.of(
            1, (a, b) -> a + b,        // Addition
            2, (a, b) -> a - b,        // Subtraktion
            3, (a, b) -> a * b,        // Multiplikation
            4, (a, b) -> b != 0 ? a / b : Double.NaN // Division mit Check auf Division durch 0
        );

        System.out.println("Welcome to the Functional Calculator!");
        
        while (true) {
            System.out.println("\nChoose an operation: ");
            System.out.println("1: Add");
            System.out.println("2: Subtract");
            System.out.println("3: Multiply");
            System.out.println("4: Divide");
            System.out.println("5: Exit");

            int choice = scanner.nextInt();

            if (choice == 5) {
                System.out.println("Exiting the calculator.");
                break;
            }

            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();

            // Berechnung über Streams und Operationen-Map
            Optional.ofNullable(operations.get(choice))
                .map(op -> op.apply(num1, num2))  // Berechnung anwenden
                .ifPresentOrElse(
                    result -> System.out.println("Result: " + result),
                    () -> System.out.println("Invalid operation or division by zero")
                );
        }

        scanner.close();
    }

}
