package com.example.Calculator;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Stack;

@ShellComponent
public class CalculatorShellCommands {

    private final Stack<Double> dblStack = new Stack<>();

    @ShellMethod(key = "push", value = "Fügt eine Zahl zum Stack hinzu.")
    public String push(double dblValue) {
        dblStack.push(dblValue);
        return "Stack: " + dblStack;
    }

    @ShellMethod(key = "add", value = "Addiert die obersten zwei Werte im Stack.")
    public String add() {
        if (dblStack.size() < 2) {
            return "Stack hat nicht genügend Werte.";
        }
        double dblB = dblStack.pop();
        double dblA = dblStack.pop();
        dblStack.push(dblA + dblB);
        return "Stack nach Addition: " + dblStack;
    }

    @ShellMethod(key = "subtract", value = "Subtrahiert die obersten zwei Werte im Stack.")
    public String subtract() {
        if (dblStack.size() < 2) {
            return "Stack hat nicht genügend Werte.";
        }
        double dblB = dblStack.pop();
        double dblA = dblStack.pop();
        dblStack.push(dblA - dblB);
        return "Stack nach Subtraktion: " + dblStack;
    }

    @ShellMethod(key = "multiply", value = "Multipliziert die obersten zwei Werte im Stack.")
    public String multiply() {
        if (dblStack.size() < 2) {
            return "Stack hat nicht genügend Werte.";
        }
        double dblB = dblStack.pop();
        double dblA = dblStack.pop();
        dblStack.push(dblA * dblB);
        return "Stack nach Multiplikation: " + dblStack;
    }

    @ShellMethod(key = "divide", value = "Teilt die obersten zwei Werte im Stack.")
    public String divide() {
        if (dblStack.size() < 2) {
            return "Stack hat nicht genügend Werte.";
        }
        double dblB = dblStack.pop();
        if (dblB == 0) {
            dblStack.push(dblB); // Rückgabe in den Stack
            return "Division durch 0 ist nicht erlaubt.";
        }
        double dblA = dblStack.pop();
        dblStack.push(dblA / dblB);
        return "Stack nach Division: " + dblStack;
    }

    @ShellMethod(key = "clear", value = "Löscht den gesamten Stack.")
    public String clear() {
        dblStack.clear();
        return "Stack geleert.";
    }

    @ShellMethod(key = "print", value = "Gibt den aktuellen Stack aus.")
    public String print() {
        return "Aktueller Stack: " + dblStack;
    }
}

