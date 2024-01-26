package lab02;

import java.util.Scanner;

/**
 * For exploring the char type.
 * @author YOUR NAME
 * @version Aug 29, 2023
 */
public class CharCodeDemo {
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Type something and press enter.");
        
        String line = input.nextLine();
        char firstLetter = line.charAt(0);
        
        System.out.println(firstLetter + " has code " + (int)firstLetter);
    }
}