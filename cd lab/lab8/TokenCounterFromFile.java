package lab8;

import java.io.*;
import java.util.*;

public class TokenCounterFromFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder code = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                code.append(line).append(" ");
            }
            reader.close();

            StringTokenizer tokenizer = new StringTokenizer(code.toString(), " \t\n\r\f;(){}[]=+-*/%!<>&|.,\"'", true);
            int count = 0;
            Set<String> uniqueTokens = new HashSet<>();

            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken().trim();
                if (!token.isEmpty()) {
                    count++;
                    uniqueTokens.add(token);
                }
            }

            System.out.println("Total Tokens: " + count);
            System.out.println("Unique Tokens: " + uniqueTokens.size());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
