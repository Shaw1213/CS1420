package lab06;

public class FractionPractice {
	
	public static void main (String[] argo ) {
		Fraction f1 = new Fraction(5,1);
		System.out.println(f1.toString());
		Fraction f2 = new Fraction(1,2);
		System.out.println(f2.toString());
		Fraction f3 = new Fraction(3,4);
		System.out.println(f3.toString());
		Fraction f4 = new Fraction(1,1);
		System.out.println(f4.toString());
		System.out.println(f3.toWholeNumber(3));

		

	}
}
