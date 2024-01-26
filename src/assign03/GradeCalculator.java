package assign03;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
/**
 * @auther Shawn and CS 1420
 * @version Sep 14, 2023
 */

public class GradeCalculator {
	/**
	 * The main method
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		Scanner fileScanner = null;
		
		while(fileScanner == null) {
			System.out.println("Enter a file name with the path: ");
			String filePath = input.nextLine();
			try {
				fileScanner = new Scanner(new File(filePath));
			} catch (FileNotFoundException e) {
				System.out.println("Error reading the file, please try agan.");
			}
		}
		input.close();
		//F:\CS1420\src\assign03\test(for testing my program only)
		
		double averageExamScore = fileScanner.nextDouble();
		double averageLabScore = fileScanner.nextDouble();
		double averageWeeklyQuizScore = fileScanner.nextDouble();
		int numberOfAssignment=fileScanner.nextInt();
		int[]assignmentScores = new int[numberOfAssignment];
		for (int i=0; i < numberOfAssignment; i++){
			assignmentScores[i] = fileScanner.nextInt();
		}
		
		fileScanner.close();
		
		double sumOfAssiments = 0;//anarage assiment score
		for(int i=0; i<assignmentScores.length; i++) {
			sumOfAssiments += assignmentScores[i]; 
		}
		double averageAssignmentScore = sumOfAssiments/assignmentScores.length; 
		
		Arrays.sort(assignmentScores);//median score
		int medianScore = 0;
			if (numberOfAssignment % 2 == 0) {
				medianScore = (assignmentScores[(numberOfAssignment / 2) - 1] 
				+ assignmentScores[numberOfAssignment / 2]) / 2;
			} else {
				medianScore = assignmentScores[numberOfAssignment / 2];
			}
			
		int zeroAssiments = 0;//zero assiments 
		
		for(int i=0; i<assignmentScores.length; i++) {
			if(assignmentScores[i] == 0) {
				zeroAssiments++;}
			}
		
		int highestScore = 0;//highest score
		
		for(int i=0; i<assignmentScores.length; i++) {
			if(assignmentScores[i]>highestScore) {
				highestScore = assignmentScores[i]; 
			}
		}	
		int lowestScore = 100;//lowesr score
		
		for(int i=0; i<assignmentScores.length; i++) {
			if(assignmentScores[i]<lowestScore) {
				lowestScore = assignmentScores[i];
			}
		}
		double finalGradeNumeric;//final grade(numeric)
		if (averageExamScore >= 65) {
			finalGradeNumeric = (0.45 * averageExamScore) 
		    		+ (0.35 * averageAssignmentScore) 
		    		+ (0.10 * averageLabScore) 
		    		+ (0.10 * averageWeeklyQuizScore);
		} else {
			finalGradeNumeric = averageExamScore;
		}
		
		String finalGradeLetter = null;
			if (finalGradeNumeric >= 93) {
			    finalGradeLetter = "A";
			} else if (finalGradeNumeric >= 90) {
			    finalGradeLetter = "A-";
			} else if (finalGradeNumeric >= 87) {
			    finalGradeLetter = "B+";
			} else if (finalGradeNumeric >= 83) {
			    finalGradeLetter = "B";
			} else if (finalGradeNumeric >= 80) {
			    finalGradeLetter = "B-";
			} else if (finalGradeNumeric >= 77) {
			    finalGradeLetter = "C+";
			} else if (finalGradeNumeric >= 73) {
			    finalGradeLetter = "C";
			} else if (finalGradeNumeric >= 70) {
			    finalGradeLetter = "C-";
			} else if (finalGradeNumeric >= 67) {
			    finalGradeLetter = "D+";
			} else if (finalGradeNumeric >= 63) {
			    finalGradeLetter = "D";
			} else if (finalGradeNumeric >= 60) {
			    finalGradeLetter = "D-";
			} else {
			    finalGradeLetter = "E";
			}
			
			
			
		
	System.out.printf("Average assignment score: %.2f%n", averageAssignmentScore);
	System.out.println("Median Assignment Score: " + medianScore);
	System.out.println("Number of 0 Assignment Scores: " + zeroAssiments);
	System.out.println("Highest assignment score: " + highestScore);
	System.out.println("Lowest assignment score: " + lowestScore);
	System.out.printf("Course grade (numeric): %.2f%n", finalGradeNumeric);
	System.out.println("Course grade (letter): " + finalGradeLetter);
	}
}
