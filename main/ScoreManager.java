package main;

import java.io.*;
import java.util.*;

public class ScoreManager {
    private static final String FILE_PATH = "scores.txt";

    // Reads scores
    public static List<Integer> readScores() {
        List<Integer> scores = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) { // Skip empty lines
                    try {
                        scores.add(Integer.parseInt(line));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid score in file: " + line); // Log invalid entries - had trouble with whitespace
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scores.sort(Comparator.reverseOrder()); // Sort scores in descending order
        return scores;
    }

    // Saves a new score and ensures the file contains only the top 3 scores, with no duplicates
    public static void saveScore(int score) {
        // Read existing scores
        List<Integer> scores = readScores();

        // Scores get added only if they don't exist - So the whole leaderboard isn't filled with the same 1
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
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (int s : scores) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


