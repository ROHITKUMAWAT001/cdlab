package lab6;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;

public class fileInputCommentChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean insideComment = false;

        System.out.print("Enter file path: ");
        String path = scanner.nextLine();


        try {
            List<String> lines = Files.readAllLines(new File(path).toPath());

            for (String line : lines) {
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

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
