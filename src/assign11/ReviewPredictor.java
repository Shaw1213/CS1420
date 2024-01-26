package assign11;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * This class represents a review predictor that processes a file of reviews
 * and predicts scores for new review texts based on learned data.
 * 
 * @author Shawn Zhang
 * @version Dec, 7 , 2023
 */

public class ReviewPredictor {

    private HashMap<String, ArrayList<Integer>> wordScores;
    private HashMap<String, Double> averageScores;
    
    /**
     * Constructs a ReviewPredictor with the given file of reviews.
     * @param reviewsFile The file containing reviews to be processed.
     * @throws FileNotFoundException if the specified file is not found.
     */
    public ReviewPredictor(File reviewsFile) throws FileNotFoundException {
        wordScores = new HashMap<>();
        averageScores = new HashMap<>();
        processFile(reviewsFile);
        calculateAverageScores();
    }

    /**
     * Processes the reviews file and associates words with scores.
     * @param file The file to be processed.
     * @throws FileNotFoundException if the file is not found.
     */
    private void processFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                String[] parts = line.split(" ", 2);
                int score = Integer.parseInt(parts[0]);
                String text = parts[1].toLowerCase().replaceAll("[^a-z0-9 ]", "");
                String[] words = text.split(" ");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordScores.putIfAbsent(word, new ArrayList<>());
                        wordScores.get(word).add(score);
                    }
                }
            } catch (Exception e) {
                System.out.println("Warning: Skipping invalid line: " + line);
            }
        }
        scanner.close();
    }
    /**
     * Calculates the average score for each word in the reviews.
     */
    private void calculateAverageScores() {
        for (Map.Entry<String, ArrayList<Integer>> entry : wordScores.entrySet()) {
            String word = entry.getKey();
            ArrayList<Integer> scores = entry.getValue();
            double average = scores.stream().mapToDouble(a -> a).average().orElse(0.0);

            // Rounding the average score to 2 decimal places
            BigDecimal roundedAverage = new BigDecimal(average);
            roundedAverage = roundedAverage.setScale(2, RoundingMode.HALF_UP);

            if (!(average >= 1.75 && average <= 2.25)) {
                averageScores.put(word, roundedAverage.doubleValue());
            }
        }
    }

    /**
     * Predicts the score for a given review text.
     * @param reviewText The review text for which the score is to be predicted.
     * @return The predicted score, or -1.0 if no known words are found.
     */
    public double predictScore(String reviewText) {
        String processedText = reviewText.toLowerCase().replaceAll("[^a-z0-9 ]", "");
        String[] words = processedText.split(" ");
        double totalScore = 0;
        int count = 0;

        for (String word : words) {
            if (averageScores.containsKey(word)) {
                totalScore += averageScores.get(word);
                count++;
            }
        }

        return count > 0 ? totalScore / count : -1.0;
    }

    /**
     * Checks the prediction accuracy for a given review text and actual score.
     * @param reviewText The review text.
     * @param score The actual score of the review text.
     * @return The absolute difference between predicted and actual scores.
     */
    public double checkPrediction(String reviewText, double score) {
        double predictedScore = predictScore(reviewText);
        return Math.abs(predictedScore - score);
    }

    /**
     * Retrieves a list of the best words based on their average scores.
     * @param k The number of top scoring words to retrieve.
     * @return A list of the top k scoring words, or null if k is non-positive.
     */
    public List<String> bestWords(int k) {
        if (k <= 0) return null;
        List<String> words = new ArrayList<>(averageScores.keySet());
        words.sort(new OrderByPredictedScore());

        return k > words.size() ? words : words.subList(0, k);
    }
    
    /**
     * A comparator for ordering words based on their predicted scores.
     */
    public class OrderByPredictedScore implements Comparator<String> {
        @Override
        /**
         * Compares two words based on their average scores.
         * @param word1 The first word to be compared.
         * @param word2 The second word to be compared.
         * @return a negative integer, zero, or a positive integer as the
         *         first word's score is less than, equal to, or greater than the second.
         */
        public int compare(String word1, String word2) {
            double score1 = averageScores.getOrDefault(word1, 0.0);
            double score2 = averageScores.getOrDefault(word2, 0.0);
            int scoreComparison = Double.compare(score1, score2);
            return scoreComparison != 0 ? scoreComparison : word1.compareTo(word2);
        }
    }
}
