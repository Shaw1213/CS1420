package assign08;

import java.util.NoSuchElementException;


/**
 * Use BetterDynamicArray to calculate grades.
 * outputs such as average, median, highest, and lowest scores, 
 * as well as the overall course grade.
 * 
 * @author Shawn Zhang
 * @version November 9, 2023
 */
public class GradeCalculator {

	private BetterDynamicArray examScores;
    private BetterDynamicArray labScores;
    private BetterDynamicArray quizScores;
    private BetterDynamicArray assignmentScores;
    
    /**
     * Constructs a GradeCalculator.
     */
    public GradeCalculator() {
        examScores = new BetterDynamicArray();
        labScores = new BetterDynamicArray();
        quizScores = new BetterDynamicArray();
        assignmentScores = new BetterDynamicArray();	
    }
    
    /**
     * Adds a score to the exam scores dynamic array.
     * 
     * @param score The exam score to add.
     */
    public void addExamScore(int score) {
        examScores.append(score);
    }
    
    /**
     * Adds a score to the lab scores dynamic array.
     * 
     * @param score The lab score to add.
     */
    public void addLabScore(int score) {
        labScores.append(score);
    }
    
    /**
     * Adds a score to the quiz scores dynamic array.
     * 
     * @param score The quiz score to add.
     */
    public void addQuizScore(int score) {
        quizScores.append(score);
    }

    /**
     * Adds a score to the assignment scores dynamic array.
     * 
     * @param score The assignment score to add.
     */
    public void addAssignmentScore(int score) {
        assignmentScores.append(score);
    }
    
    /**
     * elper method checks if the provided dynamic array is not empty.
     * 
     * @param scores The dynamic array to check.
     * @throws NoSuchElementException If the dynamic array is empty.
     */
    private void checkNotEmpty(BetterDynamicArray scores) throws NoSuchElementException {
        if (scores.size() == 0) {
            throw new NoSuchElementException("Dynamic array is empty.");
        }
    }
    
    /**
     * Calculates the average score of the assignments.
     * 
     * @return The average score of assignments.
     * @throws NoSuchElementException If there are no assignment scores to calculate the average.
     */
    public double getAverageAssignmentScore() throws NoSuchElementException {
        if (assignmentScores.size() == 0) {
            throw new NoSuchElementException("No assignment scores to calculate average.");
        }
        int sum = 0;
        for (int i = 0; i < assignmentScores.size(); i++) {
            sum += assignmentScores.getElement(i);
        }
        return sum / (double) assignmentScores.size();
    }
    
    /**
     * Calculates the median score from the assignment scores.
     * 
     * @return The median assignment score.
     * @throws NoSuchElementException If there are no assignment scores to calculate the median.
     */
    public int getMedianAssignmentScore() throws NoSuchElementException {
        checkNotEmpty(assignmentScores);
        assignmentScores.sort();
        int middleIndex = assignmentScores.size() / 2;
        if (assignmentScores.size() % 2 == 1) {
            return assignmentScores.getElement(middleIndex);
        } else {
            double median = (assignmentScores.getElement(middleIndex - 1) );
            return (int) Math.round(median); 
        }
    }

    /**
     * Counts the number of zero scores in the assignments.
     * 
     * @return The count of zero scores in assignments.
     * @throws NoSuchElementException If assignment scores are not available.
     */
    public int getNumberOfZeroAssignmentScores() throws NoSuchElementException {
        checkNotEmpty(assignmentScores);
        int zeroCount = 0;
        for (int i = 0; i < assignmentScores.size(); i++) {
            if (assignmentScores.getElement(i) == 0) {
                zeroCount++;
            }
        }
        return zeroCount;
    }
    
    /**
     * Finds the highest score from the assignment scores.
     * 
     * @return The highest assignment score.
     * @throws NoSuchElementException If assignment scores are not available.
     */
    public int getHighestAssignmentScore() throws NoSuchElementException {
        checkNotEmpty(assignmentScores);
        int highest = Integer.MIN_VALUE;
        for (int i = 0; i < assignmentScores.size(); i++) {
            highest = Math.max(highest, assignmentScores.getElement(i));
        }
        return highest;
    }

    /**
     * Finds the lowest score from the assignment scores.
     * 
     * @return The lowest assignment score.
     * @throws NoSuchElementException If assignment scores are not available.
     */
    public int getLowestAssignmentScore() throws NoSuchElementException {
        checkNotEmpty(assignmentScores);
        int lowest = Integer.MAX_VALUE;
        for (int i = 0; i < assignmentScores.size(); i++) {
            lowest = Math.min(lowest, assignmentScores.getElement(i));
        }
        return lowest;
    }
    
    /**
     * Calculates the overall numeric course grade based on the weighted average of exams, labs,
     * quizzes, and assignments.
     * 
     * @return The overall numeric course grade.
     * @throws NoSuchElementException If any of the score categories are empty.
     */
    public double getCourseGradeNumeric() throws NoSuchElementException {
        if (examScores.size() == 0 || labScores.size() == 0 || quizScores.size() == 0 || assignmentScores.size() == 0) {
            throw new NoSuchElementException("One or more score categories are empty.");
        }

        double examsAverage = calculateAverage(examScores);
        double labsAverage = calculateAverage(labScores);
        double quizzesAverage = calculateAverage(quizScores);
        double assignmentsAverage = calculateAverage(assignmentScores);
        
        if(examsAverage < 65.0){
        	return examsAverage;
        }
        return examsAverage * 0.45 + labsAverage * 0.10 + quizzesAverage * 0.10 + assignmentsAverage * 0.35;
    }

    /**
     * Converts the numeric course grade to the corresponding letter grade.
     * 
     * @return The letter grade for the course.
     * @throws NoSuchElementException If any of the score categories are empty.
     */
    public String getCourseGradeLetter() throws NoSuchElementException {
        double numericGrade = getCourseGradeNumeric();
        return convertNumericToLetterGrade(numericGrade);
    }
    
    /**
     * Calculates the average of the values in the given dynamic array.
     * 
     * @param scores The dynamic array containing the scores.
     * @return The average of the scores.
     */
    private double calculateAverage(BetterDynamicArray scores) {
        int sum = 0;
        for (int i = 0; i < scores.size(); i++) {
            sum += scores.getElement(i);
        }
        return sum / (double) scores.size();
    }
    
    /**
     * Converts a numeric grade to a letter grade.
     * 
     * @param numericGrade The numeric grade to convert.
     * @return The corresponding letter grade.
     */
    private String convertNumericToLetterGrade(double numericGrade) {
        if (numericGrade >= 93) {
            return "A";
        } else if (numericGrade >= 90) {
            return "A-";
        } else if (numericGrade >= 87) {
            return "B+";
        } else if (numericGrade >= 83) {
            return "B";
        } else if (numericGrade >= 80) {
            return "B-";
        } else if (numericGrade >= 77) {
            return "C+";
        } else if (numericGrade >= 73) {
            return "C";
        } else if (numericGrade >= 70) {
            return "C-";
        } else if (numericGrade >= 67) {
            return "D+";
        } else if (numericGrade >= 63) {
            return "D";
        } else if (numericGrade >= 60) {
            return "D-";
        } else {
            return "E";
        }
    } 
}
