package lab07;

/**
 * This class contains a potentially buggy average method.
 * 
 * @author Prof. Parker
 * @version September 28, 2023
 */
public class Part1 {
	
	public static Fraction average(Fraction[] arr) {
	    if(arr.length == 0)
	        return null;
	    Fraction sum = new Fraction(0, 1); // Initialize sum as 0/1
	    for(int i = 0; i < arr.length; i++)
	        sum = sum.add(arr[i]);
	    return sum.multiply(new Fraction(1, arr.length)); // Divide by the number of elements to get the average
	}
}