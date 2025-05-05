package lab1;

import java.util.Scanner;

public class
Arithmetic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter arithmetic expression: ");
        String expression = scanner.nextLine();
        if (isValidArithmeticExpression(expression)) {
            System.out.println("The input is a valid arithmetic expression.");
        } else {
            System.out.println("The input is invalid Arithmetic Expression.");
        }
        scanner.close();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/'|| ch == '%' || ch == '^';
    }

    private static boolean isValidArithmeticExpression(String expression) {
        int i = 0;
        int length = expression.length();
        boolean expectingNumber = true;
        while (i < length) {
            char ch = expression.charAt(i);
            if (ch == ' ') {
                i++;
                continue;
            }
            if (expectingNumber) {
                if (Character.isDigit(ch)) {
                    while (i < length && Character.isDigit(expression.charAt(i))) {
                        i++;
                    }
                    expectingNumber = false;
                } else {
                    return false;
                }
            } else {
                if (isOperator(ch)) {
                    i++;
                    expectingNumber = true;
                } else {
                    return false;
                }
            }
        }
        return !expectingNumber;
    }

}