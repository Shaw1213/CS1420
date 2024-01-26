package assign11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the Review Predictor.
 * Provides a user interface for interacting with the ReviewPredictor.
 * 
 * @author Shawn Zhang
 * @version Dec, 7 , 2023
 */
public class ReviewPredictorApplication {

	/**
     * Entry point for the Review Predictor application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ReviewPredictor predictor = null;

        while (predictor == null) {
            try {
                System.out.println("Enter the path to the reviews file:");
                String filePath = input.nextLine();
                predictor = new ReviewPredictor(new File(filePath));
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage() + ". Please try again.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage() + ". Please try again.");
            }
        }

        boolean running = true;
        while (running) {
            try {
                System.out.println("\nChoose an option:");
                System.out.println("1. Predict Score");
                System.out.println("2. Check Prediction");
                System.out.println("3. Display Top K Words");
                System.out.println("4. Exit");

                int choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1:
                        predictScore(input, predictor);
                        break;
                    case 2:
                        checkPrediction(input, predictor);
                        break;
                    case 3:
                        displayTopKWords(input, predictor);
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        System.out.println("Exiting...");
    }

    /**
     * Handles the prediction of scores for userentered review text.
     * @param input Scanner for reading user input.
     * @param predictor The ReviewPredictor instance used for score prediction.
     */
    private static void predictScore(Scanner input, ReviewPredictor predictor) {
        String review = getValidStringInput(input, "Enter review text:");
        System.out.println("Predicted Score: " + predictor.predictScore(review));
    }

    /**
     * Handles checking the prediction accuracy for user entered review text and score.
     * @param input Scanner for reading user input.
     * @param predictor The ReviewPredictor instance used for checking prediction accuracy.
     */
    private static void checkPrediction(Scanner input, ReviewPredictor predictor) {
        String review = getValidStringInput(input, "Enter review text:");
        double score = getValidDoubleInput(input, "Enter actual score:");
        System.out.println("Prediction Accuracy: " + predictor.checkPrediction(review, score));
    }

    /**
     * Displays the top K words based on their average scores.
     * @param input Scanner for reading user input.
     * @param predictor The ReviewPredictor instance used for retrieving top words.
     * @param k The number of top words to display.
     */
    private static void displayTopKWords(Scanner input, ReviewPredictor predictor) {
        int k = getValidIntegerInput(input, "Enter k value:");
        List<String> topWords = predictor.bestWords(k);
        System.out.println("Top " + k + " words: " + topWords);
    }

    /**
     * Gets a valid non empty string input from the user.
     * @param input Scanner for reading user input.
     * @param prompt The prompt to be displayed to the user.
     * @return A valid non empty string input.
     */
    private static String getValidStringInput(Scanner input, String prompt) {
        String value;
        while (true) {
            System.out.println(prompt);
            value = input.nextLine();
            if (!value.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Invalid input. Please enter a non-empty string.");
            }
        }
        return value;
    }

    /**
     * Gets a valid integer input from the user.
     * @param input Scanner for reading user input.
     * @param prompt The prompt to be displayed to the user.
     * @return A valid integer input.
     */
    private static int getValidIntegerInput(Scanner input, String prompt) {
        int value;
        while (true) {
            try {
                System.out.println(prompt);
                value = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    }

    /**
     * Gets a valid double input from the user.
     * @param input Scanner for reading user input.
     * @param prompt The prompt to be displayed to the user.
     * @return A valid double input.
     */
    private static double getValidDoubleInput(Scanner input, String prompt) {
        double value;
        while (true) {
            try {
                System.out.println(prompt);
                value = Double.parseDouble(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    }
}
//src/assign11/MovieReviews.txt
