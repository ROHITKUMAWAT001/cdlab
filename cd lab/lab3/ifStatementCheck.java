package lab3;

import java.util.Scanner;

public class ifStatementCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an if statement for checking:");
        String input = scanner.nextLine();
        scanner.close();

        if (input.startsWith("if(") && input.endsWith(")")) {
            String condition = input.substring(3, input.length() - 1).trim();
            String regex = "([a-zA-Z0-9]+\\s*(==|!=|>|<|>=|<=)\\s*[a-zA-Z0-9]+)(\\s*(\\&\\&|\\|\\|)\\s*[a-zA-Z0-9]+\\s*(==|!=|>|<|>=|<=)\\s*[a-zA-Z0-9]+)*";

            if (condition.matches(regex)) {
                System.out.println("Valid");
            } else {
                System.out.println("Invalid condition");
            }
        } else {
            System.out.println("Error: Must start with 'if(' and end with ')'");
        }
    }
}
