package lab7;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileAnalysis {
    public static void main(String[] args) {
        try {
            System.out.print("Enter file path: ");
            String path = new java.util.Scanner(System.in).nextLine();

            File file = new File(path);
            if (!file.exists()) {
                System.out.println("File not found.");
                return;
            }

            long byteCount = file.length();

            System.out.println("File Name: " + file.getName());
            System.out.println("File Size (bytes): " + byteCount);

            String mimeType = Files.probeContentType(file.toPath());
            if (mimeType != null && mimeType.startsWith("text")) {
                int lineCount = 0;
                int wordCount = 0;

                for (String line : Files.readAllLines(file.toPath())) {
                    lineCount++;
                    wordCount += line.trim().split("\\s+").length;
                }

                System.out.println("Lines (only for text files): " + lineCount);
                System.out.println("Words (only for text files): " + wordCount);
            } else {
                System.out.println("Binary file detected — word/line count skipped.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
