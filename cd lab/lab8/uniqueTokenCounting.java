 package lab8;
import java.util.*;

public class uniqueTokenCounting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder code = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("END")) {
            code.append(line).append(" ");
        }
        StringTokenizer tokenizer = new StringTokenizer(code.toString(), " \t\n\r\f;(){}[]=+-*/%!<>&|.,\"'", true);
        int count = 0;
        Set<String> uniqueTokens = new HashSet<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (!token.isEmpty()) {
                count++;
                uniqueTokens.add(token);
                System.out.println("Token " + count + ": " + token);
            }
        }
        System.out.println("Total Tokens: " + count);
        System.out.println("Unique Tokens: " + uniqueTokens.size());
    }
}

