package com.example.Calculator;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.ArrayList;

@ShellComponent
public class CalculatorShellCommands {

    private List<Double> dblStack = List.of(); // Unveränderlicher Stack

    @ShellMethod(key = "push", value = "Fügt eine Zahl zum Stack hinzu.")
    public String push(double value) {
        dblStack = immutablePush(dblStack, value);
        return "Stack: " + dblStack;
    }

    @ShellMethod(key = "pop", value = "Entfernt das oberste Element vom Stack.")
    public String pop() {
        if (dblStack.isEmpty()) {
            return "Fehler: Der Stack ist leer.";
        }
        double removed = dblStack.get(0);
        dblStack = dblStack.subList(1, dblStack.size());
        return "Entfernt: " + removed + " | Stack: " + dblStack;
    }

    @ShellMethod(key = "add", value = "Addiert die obersten zwei Werte im Stack.")
    public String add() {
        return binaryOperation((a, b) -> a + b, "Addition");
    }

    @ShellMethod(key = "subtract", value = "Subtrahiert die obersten zwei Werte im Stack.")
    public String subtract() {
        return binaryOperation((a, b) -> b - a, "Subtraktion");
    }

    @ShellMethod(key = "multiply", value = "Multipliziert die obersten zwei Werte im Stack.")
    public String multiply() {
        return binaryOperation((a, b) -> a * b, "Multiplikation");
    }

    @ShellMethod(key = "divide", value = "Teilt die obersten zwei Werte im Stack.")
    public String divide() {
        return binaryOperation((a, b) -> {
            if (a == 0) throw new ArithmeticException("Division durch 0");
            return b / a;
        }, "Division");
    }

    @ShellMethod(key = "power", value = "Hebt die zweitoberste Zahl im Stack auf die Potenz der obersten Zahl.")
    public String power() {
        return binaryOperation((a, b) -> Math.pow(b, a), "Potenz");
    }

    @ShellMethod(key = "sqrt", value = "Berechnet die Quadratwurzel der obersten Zahl im Stack.")
    public String sqrt() {
        if (dblStack.isEmpty()) {
            return "Fehler: Der Stack ist leer.";
        }
        double value = dblStack.get(0);
        if (value < 0) {
            return "Fehler: Quadratwurzel von negativen Zahlen ist nicht definiert.";
        }
        double result = Math.sqrt(value);
        dblStack = immutablePush(dblStack.subList(1, dblStack.size()), result);
        return "Quadratwurzel Ergebnis: " + result + " | Stack: " + dblStack;
    }

    @ShellMethod(key = "sum-all", value = "Summiert alle Werte im Stack.")
    public String sumAll() {
        double sum = dblStack.stream().reduce(0.0, Double::sum);
        dblStack = List.of(sum);
        return "Summe aller Werte: " + sum + " | Stack: " + dblStack;
    }

    @ShellMethod(key = "product-all", value = "Berechnet das Produkt aller Werte im Stack.")
    public String productAll() {
        double product = dblStack.stream().reduce(1.0, (a, b) -> a * b);
        dblStack = List.of(product);
        return "Produkt aller Werte: " + product + " | Stack: " + dblStack;
    }

    @ShellMethod(key = "average", value = "Berechnet den Durchschnitt aller Werte im Stack.")
    public String average() {
        if (dblStack.isEmpty()) {
            return "Fehler: Der Stack ist leer.";
        }
        double avg = dblStack.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        return "Durchschnitt: " + avg + " | Stack: " + dblStack;
    }

    @ShellMethod(key = "min", value = "Findet den minimalen Wert im Stack.")
    public String min() {
        return dblStack.stream().min(Double::compareTo)
                .map(min -> "Minimalwert: " + min + " | Stack: " + dblStack)
                .orElse("Fehler: Der Stack ist leer.");
    }

    @ShellMethod(key = "max", value = "Findet den maximalen Wert im Stack.")
    public String max() {
        return dblStack.stream().max(Double::compareTo)
                .map(max -> "Maximalwert: " + max + " | Stack: " + dblStack)
                .orElse("Fehler: Der Stack ist leer.");
    }

    @ShellMethod(key = "clear", value = "Leert den Stack.")
    public String clear() {
        dblStack = List.of();
        return "Stack geleert.";
    }

    @ShellMethod(key = "stack", value = "Zeigt den aktuellen Stack an.")
    public String stack() {
        return "Aktueller Stack: " + dblStack;
    }

    private List<Double> immutablePush(List<Double> stack, double value) {
        List<Double> newStack = new ArrayList<>(stack);
        newStack.add(0, value); // Neues Element oben hinzufügen
        return List.copyOf(newStack); // Unveränderliche Kopie zurückgeben
    }

    private String binaryOperation(BinaryOperator operation, String operationName) {
        if (dblStack.size() < 2) {
            return "Fehler: Der Stack hat nicht genügend Werte für " + operationName + ".";
        }
        try {
            double result = operation.apply(dblStack.get(0), dblStack.get(1));
            dblStack = immutablePush(dblStack.subList(2, dblStack.size()), result);
            return operationName + " Ergebnis: " + result + " | Stack: " + dblStack;
        } catch (ArithmeticException e) {
            return "Fehler: " + e.getMessage();
        }
    }

    @FunctionalInterface
    private interface BinaryOperator {
        double apply(double a, double b);
    }
}

