package LAB5;

import java.util.Scanner;

public class declStatement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a declarative statement:");
        String input = scanner.nextLine();

        if (isValidDeclaration(input)) {
            System.out.println("The declarative statement is syntactically valid.");
        } else {
            System.out.println("The declarative statement is syntactically invalid.");
        }
        scanner.close();
    }

    private static boolean isValidDeclaration(String statement) {
        statement = statement.trim();
        if (!statement.endsWith(";")) return false;

        statement = statement.substring(0, statement.length() - 1).trim();
        int firstSpace = statement.indexOf(' ');
        if (firstSpace == -1) return false;

        String type = statement.substring(0, firstSpace);
        String rest = statement.substring(firstSpace).trim();

        if (!type.matches("int|float|double|char|String")) return false;

        return rest.matches("[a-zA-Z_$][a-zA-Z_$0-9]*") || rest.matches("[a-zA-Z_$][a-zA-Z_$0-9]*\\s*=.*");
    }
}
