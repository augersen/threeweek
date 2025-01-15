package main;

import java.io.*;
import java.util.*;

public class ScoreManager {
    private static final String DEFAULT_FILE_PATH = "scores.txt";

    // Get the file path based on the current modifier
    private static String getFilePath(String modifier) {
        return modifier.equals("NoModifier") ? DEFAULT_FILE_PATH : modifier + "_scores.txt";
    }

    // Reads scores from the appropriate file
    public static List<Integer> readScores(String modifier) {
        List<Integer> scores = new ArrayList<>();
        String filePath = getFilePath(modifier);

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    try {
                        scores.add(Integer.parseInt(line));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid score in file: " + line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No scores file found for " + modifier + ". A new one will be created.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        scores.sort(Comparator.reverseOrder());
        return scores;
    }

    // Saves a new score to the appropriate file
    public static void saveScore(String modifier, int score) {
        String filePath = getFilePath(modifier);
        System.out.println("Saving score to file: " + filePath); // Debug log

        // Read existing scores
        List<Integer> scores = readScores(modifier);

        // Add score only if it doesn't already exist
        if (!scores.contains(score)) {
            scores.add(score);
        }

        // Sort scores in descending order
        scores.sort(Comparator.reverseOrder());

        // Keep only the top 3 scores
        if (scores.size() > 3) {
            scores = scores.subList(0, 3);
        }

        // Save the updated scores back to the file
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int s : scores) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
