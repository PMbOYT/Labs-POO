import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class ChangeDetectionSystem {

    private static final String folderPath = "./"; // Folder path (same folder as the script)
    private static Map<String, Long> fileInfoMap = new HashMap<>();
    private static Date lastSnapshot = new Date();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File folder = new File(folderPath);

        // Load initial snapshot of files
        loadFileInfo(folder);

        while (true) {
            // Display menu
            System.out.println("\nChoose an action:");
            System.out.println("1. commit - Update snapshot time");
            System.out.println("2. info - Show information about a file");
            System.out.println("3. status - Show file status");
            System.out.println("4. exit - Exit the program");

            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "commit":
                    commitSnapshot();
                    break;

                case "info":
                    System.out.print("Enter filename: ");
                    String filename = scanner.nextLine().trim();
                    showFileInfo(filename);
                    break;

                case "status":
                    showFileStatus();
                    break;

                case "exit":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid command, try again.");
                    break;
            }
        }
    }

    private static void loadFileInfo(File folder) {
        System.out.println("Loading files from folder...");
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileInfoMap.put(file.getName(), file.lastModified());
                }
            }
        }
    }

    private static void commitSnapshot() {
        lastSnapshot = new Date();
        System.out.println("Snapshot updated at: " + lastSnapshot);
        // Reset the status of all files to "clean" after commit
        fileInfoMap.forEach((fileName, lastModified) -> {
            fileInfoMap.put(fileName, new File(folderPath + fileName).lastModified());
        });
    }

    private static void showFileInfo(String filename) {
        File file = new File(folderPath + filename);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        System.out.println("File Info:");
        System.out.println("Filename: " + filename);
        System.out.println("Extension: " + getFileExtension(file));
        System.out.println("Creation Date: " + new Date(file.lastModified()));

        // Show file-specific info based on type
        if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg")) {
            System.out.println(getImageDimensions(file));
        } else if (file.getName().endsWith(".txt")) {
            showTextFileInfo(file);
        } else if (file.getName().endsWith(".py") || file.getName().endsWith(".java")) {
            showProgramFileInfo(file);
        } else {
            System.out.println("No specific info for this file type.");
        }
    }

    private static void showTextFileInfo(File file) {
        try (Scanner fileScanner = new Scanner(file)) {
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineCount++;
                wordCount += line.split("\\s+").length;
                charCount += line.length();
            }

            System.out.println("Line count: " + lineCount);
            System.out.println("Word count: " + wordCount);
            System.out.println("Character count: " + charCount);
        } catch (IOException e) {
            System.out.println("Error reading text file.");
        }
    }

    private static void showProgramFileInfo(File file) {
        try (Scanner fileScanner = new Scanner(file)) {
            int lineCount = 0;
            int classCount = 0;
            int methodCount = 0;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineCount++;

                if (line.contains("class ")) {
                    classCount++;
                }

                if (line.contains("def ") || line.contains("public") || line.contains("private") || line.contains("protected")) {
                    methodCount++;
                }
            }

            System.out.println("Line count: " + lineCount);
            System.out.println("Class count: " + classCount);
            System.out.println("Method count: " + methodCount);
        } catch (IOException e) {
            System.out.println("Error reading program file.");
        }
    }

    private static String getImageDimensions(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            if (image != null) {
                int width = image.getWidth();
                int height = image.getHeight();
                return "Width: " + width + "px, Height: " + height + "px";
            } else {
                return "Unable to read image dimensions.";
            }
        } catch (IOException e) {
            return "Error reading image file.";
        }
    }

    private static void showFileStatus() {
        System.out.println("File Status (since last snapshot):");

        File[] files = new File(folderPath).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    long lastModified = file.lastModified();
                    if (lastModified > fileInfoMap.getOrDefault(file.getName(), 0L)) {
                        System.out.println(file.getName() + " has changed.");
                    } else {
                        System.out.println(file.getName() + " is unchanged.");
                    }
                }
            }
        }
    }

    // Utility method to get file extension
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "No extension";
    }
}
