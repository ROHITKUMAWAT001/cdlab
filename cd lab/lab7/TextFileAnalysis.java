package lab7;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TextFileAnalysis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter file path: ");
        String path = sc.nextLine();

        int charCount = 0;
        int wordCount = 0;
        int lineCount = 0;

        try {
            File file = new File(path);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineCount++;

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != ' ') {
                        charCount++;
                    }
                }

                String[] words = line.trim().split("\\s+");
                wordCount += words.length;
            }

            System.out.println("Characters (excluding spaces): " + charCount);
            System.out.println("Words: " + wordCount);
            System.out.println("Lines: " + lineCount);

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
