package lab4;

import java.util.HashMap;
import java.util.Scanner;

class symboltable {
    private HashMap<String, String> table; // Stores variable name and type

    public symboltable() {
        table = new HashMap<>();
    }

    // Add a variable to the symbol table
    public void addVariable(String name, String type) {
        table.put(name, type);
        System.out.println("Added: " + name + " -> " + type);
    }

    // Display the symbol table
    public void display() {
        System.out.println("\nSymbol Table:");
        System.out.println("Variable\tType");
        System.out.println("----------------------");
        for (String key : table.keySet()) {
            System.out.println(key + "\t\t" + table.get(key));
        }
    }
}

public class SimpleSymbolTableDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        symboltable symbolTable = new symboltable();

        System.out.print("Enter number of variables: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Enter variable name: ");
            String name = scanner.nextLine();
            System.out.print("Enter variable type (int, float, etc.): ");
            String type = scanner.nextLine();
            symbolTable.addVariable(name, type);
        }

        symbolTable.display();
        scanner.close();
    }
}
