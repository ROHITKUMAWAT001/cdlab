package lab6;

import java.util.Scanner;

public class CommentChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean insideComment = false;
        System.out.println("Enter C code line by line (type END to finish):");

        while (true) {
            String line = scanner.nextLine();

            if (line.equals("END")) break;

            int i = 0;
            while (i < line.length() - 1) {
                String twoChars = line.substring(i, i + 2);

                if (!insideComment && twoChars.equals("/*")) {
                    insideComment = true;
                    i += 2;
                } else if (insideComment && twoChars.equals("*/")) {
                    insideComment = false;
                    i += 2;
                } else {
                    i++;
                }
            }
        }
        

        if (insideComment) {
            System.out.println("Warning: Unclosed comment detected (/* without */)");
        } else {
            System.out.println("All comments are properly closed.");
        }
    }
}
