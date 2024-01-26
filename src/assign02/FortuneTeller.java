package assign02;
//CS1420 Assignment 1 by Shawn Zhang(September 5,2023)
import java.util.Scanner;

public class FortuneTeller {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter your name (or 'exit' to quit): ");
            String name = input.nextLine();

            if (name.equals("exit")) 
            {
                System.out.println("Goodbye");
                break; 
            }

            int sum = 0;
            for (int inp = 0; inp < name.length(); inp++) 
            {
                char ch = name.charAt(inp);
                sum += ch;
            }

            int happiness = (sum % 42/7);
            int career = (sum % 30/5);
            int love = (sum % 41/8);
            int luckyNumber = sum;
            String fortuneType = (happiness + career + love > 7) ? "good" : "bad";
            
            System.out.println("Your fortune is " + fortuneType);
            System.out.println("Happiness: " + happiness);
            System.out.println("Career: " + career);
            System.out.println("Love: " + love);
            System.out.println("Lucky number: " + luckyNumber);
        }

        input.close(); 
    }
}
