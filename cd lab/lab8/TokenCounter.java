package lab8;
import java.util.*;

public class TokenCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder code = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("END")) {
            code.append(line).append(" ");
        }
        StringTokenizer tokenizer = new StringTokenizer(code.toString(), " \t\n\r\f;(){}[]=+-*/%!<>&|.,\"'", true);
        int count = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            token = token.trim();
            if (!token.isEmpty()) {
                System.out.println("Token " + (count + 1) + ": " + token);
                count++;
            }
        }
        System.out.println("Total Tokens: " + count);
    }
}


