package assign04;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Assignment 04 Method practice 
 * 
 * @author Shawn Zhang
 * @version September 29, 2023
 */

public class MethodPractice {
	/**
	 * Chekcing all the methods givin and print them out.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		// Checking kilometersToMiles method
	    System.out.println("Checking kilometersToMiles(50.0). Expecting a result of 31. The actual result is " + kilometersToMiles(50.0) + ".");
	    System.out.println("Checking kilometersToMiles(1.0). Expecting a result of 0. The actual result is " + kilometersToMiles(1.0) + ".");
	    System.out.println("Checking kilometersToMiles(0.0). Expecting a result of 0. The actual result is " + kilometersToMiles(0.0) + ".");
	    System.out.println("Checking kilometersToMiles(15.5). Expecting a result of 9. The actual result is " + kilometersToMiles(15.5) + ".");

	    // Checking powerOfTwo method
	    System.out.println("Checking powerOfTwo(0). Expecting a result of 1. The actual result is " + powerOfTwo(0) + ".");
	    System.out.println("Checking powerOfTwo(5). Expecting a result of 32. The actual result is " + powerOfTwo(5) + ".");
	    System.out.println("Checking powerOfTwo(8). Expecting a result of 256. The actual result is " + powerOfTwo(8) + ".");
	    System.out.println("Checking powerOfTwo(10). Expecting a result of 1024. The actual result is " + powerOfTwo(10) + ".");

	    // Checking shiftCipher method
	    System.out.println("Checking shiftCipher(\"hello\", 3). Expecting a result of khoor. The actual result is " + shiftCipher("hello", 3) + ".");
	    System.out.println("Checking shiftCipher(\"world\", 5). Expecting a result of btwqi. The actual result is " + shiftCipher("world", 5) + ".");
	    System.out.println("Checking shiftCipher(\"java\", 2). Expecting a result of lcxc. The actual result is " + shiftCipher("java", 2) + ".");
	    System.out.println("Checking shiftCipher(\"programming\", 4). Expecting a result of vxsoxwwmsrwi. The actual result is " + shiftCipher("programming", 4) + ".");

	    // Checking countIntegerZeros method
	    System.out.println("Checking countIntegerZeros(new Scanner(\"hello 0 10 0.0 string0 0\")). Expecting a result of 2. The actual result is " + countIntegerZeros(new Scanner("hello 0 10 0.0 string0 0")) + ".");
	    System.out.println("Checking countIntegerZeros(new Scanner(\"0 0 0 0 0\")). Expecting a result of 5. The actual result is " + countIntegerZeros(new Scanner("0 0 0 0 0")) + ".");
	    System.out.println("Checking countIntegerZeros(new Scanner(\"1 2 3 4 5\")). Expecting a result of 0. The actual result is " + countIntegerZeros(new Scanner("1 2 3 4 5")) + ".");
	    System.out.println("Checking countIntegerZeros(new Scanner(\"0.0 0.1 1.0 10.0\")). Expecting a result of 0. The actual result is " + countIntegerZeros(new Scanner("0.0 0.1 1.0 10.0")) + ".");

	    // Checking averageInRange method
	    System.out.println("Checking averageInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 2, 5). Expecting a result of 4.5. The actual result is " + averageInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 2, 5) + ".");
	    System.out.println("Checking averageInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 5, 2). Expecting a result of 0.0. The actual result is " + averageInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 5, 2) + ".");
	    System.out.println("Checking averageInRange(new int[]{10, 20, 30, 40, 50}, 1, 3). Expecting a result of 30.0. The actual result is " + averageInRange(new int[]{10, 20, 30, 40, 50}, 1, 3) + ".");
	    System.out.println("Checking averageInRange(new int[]{5, 10, 15, 20, 25, 30}, 0, 5). Expecting a result of 17.5. The actual result is " + averageInRange(new int[]{5, 10, 15, 20, 25, 30}, 0, 5) + ".");

	    // Checking generateArray method
	    System.out.println("Checking generateArray(5). Expecting a result of [0, 1, 2, 3, 4]. The actual result is " + Arrays.toString(generateArray(5)) + ".");
	    System.out.println("Checking generateArray(0). Expecting a result of []. The actual result is " + Arrays.toString(generateArray(0)) + ".");
	    System.out.println("Checking generateArray(3). Expecting a result of [0, 1, 2]. The actual result is " + Arrays.toString(generateArray(3)) + ".");
	    System.out.println("Checking generateArray(7). Expecting a result of [0, 1, 2, 3, 4, 5, 6]. The actual result is " + Arrays.toString(generateArray(7)) + ".");

	    // Checking binaryToDecimal method
	    System.out.println("Checking binaryToDecimal(\"1010\"). Expecting a result of 10. The actual result is " + binaryToDecimal("1010") + ".");
	    System.out.println("Checking binaryToDecimal(\"1101\"). Expecting a result of 13. The actual result is " + binaryToDecimal("1101") + ".");
	    System.out.println("Checking binaryToDecimal(\"1001\"). Expecting a result of 9. The actual result is " + binaryToDecimal("1001") + ".");
	    System.out.println("Checking binaryToDecimal(\"1111\"). Expecting a result of 15. The actual result is " + binaryToDecimal("1111") + ".");
	}
	
	/**
	 * Converts kilometers to miles and rounds down to the closest mile. 
	 * 
	 * @param kilometers The distance in killometers.
	 * @return The same distance but in miles.
	 */
	public static int kilometersToMiles(double kilometers) {
        return (int) (kilometers * 0.621371);
    }
	
	/**
	 * Calculates the power of 2 raised to the exponent.
	 * 
	 * @param exponent The exponent which two is raised.
	 * @return The result of 2 raised to exponent.
	 */
	  public static int powerOfTwo(int exponent) {
	        int result = 1;
	        for (int i = 0; i < exponent; i++) {
	            result *= 2;
	        }
	        return result;
	  }
	  /**
	   * Encrypts a message by using a shift cipher.
	   * 
	   * @param message The message to be encrypted.
	   * @param key The shift amount for encryption. 
	   * @return The encrypted message.
	   */
	  public static String shiftCipher(String message, int key) {
	        char[] messageChars = message.toCharArray();
	        
	        for (int i = 0; i < messageChars.length; i++) {
	            // To Ensure the character is within the ASCII printable range (32 to 126)
	            if (messageChars[i] >= 32 && messageChars[i] <= 126) {
	                messageChars[i] = (char) ((messageChars[i] - 32 + key) % 95 + 32);
	            }
	        }
	        
	        return String.valueOf(messageChars);
	    }
	  
	    /**
	     * Counts the numbers of zeros in text.
	     * 
	     * @param input The Scanner object containing the input text.
	     * @return The number of zero integers.
	     */
	  public static int countIntegerZeros(Scanner input) {
		    int count = 0;
		    while (input.hasNext()) {
		        if (input.hasNextInt()) {
		            if (input.nextInt() == 0) {
		                count++;
		            }
		        } else {
		            input.next();
		        }
		    }
		    return count;
		}
	    /**
	     * Calculates the avrage of array element in range.
	     * 
	     * @param arr The array of intergers.
	     * @param start The starting index of the range.
	     * @param end The ending infex of the range.
	     * @return The avrage of elements in range.
	     */
	  
	  public static double averageInRange(int[] arr, int start, int end) {
	        if (start > end || start < 0 || end >= arr.length) {
	            return 0.0;
	        }
	        int sum = 0;
	        for (int i = start; i <= end; i++) {
	            sum += arr[i];
	        }
	        return (double) sum / (end - start + 1);
	    }
	  /**
	   * Generate an array of a specified length.
	   * 
	   * @param length The length of the array that will be generated.
	   * @return The array that is beeing generated.
	   */
	  public static int[] generateArray(int length) {
	        int[] arr = new int[length];
	        for (int i = 0; i < length; i++) {
	            arr[i] = i;
	        }
	        return arr;
	    }
  	  /**
  	   * Converts a binary number to decimal.
  	   * 
  	   * @param binary The binary numver a string.
  	   * @return The decimal verson of the binary numver.
  	   */
	  public static int binaryToDecimal(String binary) {
	        int decimalValue = 0;
	        int length = binary.length();

	        for (int i = 0; i < length; i++) {
	            char bit = binary.charAt(length - 1 - i);
	            if (bit == '1') {
	                decimalValue += powerOfTwo(i);
	            }
	        }

	        return decimalValue;
	    }
	  
	
	
}
