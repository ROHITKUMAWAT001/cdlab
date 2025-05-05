package lab2;


import java.util.Scanner;
import java.util.Stack;

public class InfixToPostfix {
    public static boolean theOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static boolean theNumber(char ch) {
        return Character.isDigit(ch);
    }

    public static boolean theValidExp(String exp) {
        int length = exp.length();
        boolean numberExpectHere = true;
        int Count = 0;
        for (int i = 0; i < length; ) {
            char ch = exp.charAt(i);
            if (ch == ' ') {
                i++;
                continue;
            }
            if (numberExpectHere) {
                if (theNumber(ch)) {
                    while (i < length && theNumber(exp.charAt(i))) {
                        i++;
                    }
                    numberExpectHere = false;
                } else if (ch == '(') {
                    Count++;
                    i++;
                } else {
                    return false;
                }
            } else {
                if (theOperator(ch)) {
                    i++;
                    numberExpectHere = true;
                } else if (ch == ')') {
                    if (Count == 0) {
                        return false;
                    }
                    Count--;
                    i++;
                } else {
                    return false;
                }
            }
        }
        return !numberExpectHere && Count == 0;
    }

    public static int myOrder(char meraOperator) {
        switch (meraOperator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    public static String infixToPostfix(String exp) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (ch == ' ') {
                continue;
            }
            if (theNumber(ch)) {
                while (i < exp.length() && theNumber(exp.charAt(i))) {
                    postfix.append(exp.charAt(i));
                    i++;
                }
                postfix.append(' ');
                i--;
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.pop();
            } else if (theOperator(ch)) {
                while (!stack.isEmpty() && myOrder(stack.peek()) >= myOrder(ch) && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(' ');
        }
        return postfix.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" please enter a arithmetic expression here: ");
        String expression = scanner.nextLine();
        scanner.close();
        if (theValidExp(expression)) {
            System.out.println("This is valid.");
            String postfix = infixToPostfix(expression);
            System.out.println("so your postfix notation for the exp=> { " + expression + " } is this ==> " + postfix);
        } else {
            System.out.println("This is invalid.");
        }
    }
}