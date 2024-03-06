import java.util.Scanner;
import java.util.Random;

public class Random_Password_Generator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Prompt the user to enter the desired length of the password
        System.out.print("Enter the desired length of the password: ");
        int passwordLength = sc.nextInt();
        
        // Prompt the user to specify character types
        System.out.print("Include numbers (yes/no): ");
        boolean includeNumbers = sc.next().equalsIgnoreCase("yes");
        
        System.out.print("Include lowercase letters (yes/no): ");
        boolean includeLowercase = sc.next().equalsIgnoreCase("yes");
        
        System.out.print("Include uppercase letters (yes/no): ");
        boolean includeUppercase = sc.next().equalsIgnoreCase("yes");
        
        System.out.print("Include special characters (yes/no): ");
        boolean includeSpecialCharacters = sc.next().equalsIgnoreCase("yes");
        
        // Generate the password
        String password = generatePassword(passwordLength, includeNumbers, includeLowercase, includeUppercase, includeSpecialCharacters);
        
        // Display the generated password
        System.out.println("Generated password: " + password);
        
        sc.close();
    }
    
    public static String generatePassword(int length, boolean includeNumbers, boolean includeLowercase, boolean includeUppercase, boolean includeSpecialCharacters) {
        StringBuilder password = new StringBuilder();
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()-_+=<>?";

        String allCharacters = "";

        if (includeLowercase) {
            allCharacters += lowercaseLetters;
        }
        if (includeUppercase) {
            allCharacters += uppercaseLetters;
        }
        if (includeNumbers) {
            allCharacters += numbers;
        }
        if (includeSpecialCharacters) {
            allCharacters += specialCharacters;
        }

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allCharacters.length()); // Get random characters from allCharacters
            password.append(allCharacters.charAt(randomIndex)); //append all randomIndex
        }

        return password.toString();
    }
}
