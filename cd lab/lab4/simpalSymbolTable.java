package lab4;

import java.util.*;
import java.util.regex.*;

class SymInfo {
    String name, type, scope, value;

    public SymInfo(String name, String type, String scope, String value) {
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s", name, type, scope, value);
    }
}

class SymTable {
    private final List<SymInfo> symbols;
    private final Map<String, String> values;
    private boolean inFunc;

    public SymTable() {
        symbols = new ArrayList<>();
        values = new HashMap<>();
        inFunc = false;
    }

    public void analyze(String code) {
        String[] lines = code.split("\n");
        for (String line : lines) {
            line = line.trim();

            Matcher func = Pattern.compile("(void|int|float|char|double)\\s+(\\w+)\\s*\\(.*\\)\\s*\\{?").matcher(line);
            if (func.find()) {
                symbols.add(new SymInfo(func.group(2), func.group(1), "Global", "Function"));
                inFunc = true;
            } else if (line.equals("}")) {
                inFunc = false;
            } else {
                Matcher var = Pattern.compile("(int|float|double|String|char)\\s+(\\w+)\\s*(=\\s*([^;]+))?\\s*;").matcher(line);
                if (var.find()) {
                    String type = var.group(1);
                    String name = var.group(2);
                    String value = var.group(4) != null ? eval(var.group(4)) : "null";
                    String scope = inFunc ? "Local" : "Global";
                    symbols.add(new SymInfo(name, type, scope, value));
                    values.put(name, value);
                } else {
                    Matcher assign = Pattern.compile("(\\w+)\\s*=\\s*([^;]+)\\s*;").matcher(line);
                    if (assign.find()) {
                        String name = assign.group(1);
                        String value = eval(assign.group(2));
                        values.put(name, value);
                        for (SymInfo s : symbols) {
                            if (s.name.equals(name)) {
                                s.value = value;
                            }
                        }
                    }
                }
            }
        }
    }

    private String eval(String expr) {
        for (String key : values.keySet()) {
            expr = expr.replaceAll("\\b" + key + "\\b", values.get(key));
        }
        return expr;
    }

    public void printTable() {
        System.out.println("\nSymbol Table:");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Name", "Type", "Scope", "Value");
        System.out.println("--------------------------------------------------------------");
        for (SymInfo s : symbols) {
            System.out.println(s);
        }
    }
}

public class simpalSymbolTable {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SymTable symTable = new SymTable();

        System.out.println("Enter code (END to stop):");
        StringBuilder code = new StringBuilder();
        while (true) {
            String line = input.nextLine();
            if (line.equals("END")) break;
            code.append(line).append("\n");
        }

        symTable.analyze(code.toString());
        symTable.printTable();
        input.close();
    }
}
