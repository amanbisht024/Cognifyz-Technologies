import java.util.Scanner;

public class Password_Strength_Checker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt the user to input a password
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        // Analyze the strength of the password
        int length = password.length();
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecialChar = !password.matches("[A-Za-z0-9]*");

        // Provide feedback on password strength
        System.out.println("Password Strength Analysis:");
        System.out.println("Length: " + length);
        System.out.println("Contains Uppercase Letters: " + hasUppercase);
        System.out.println("Contains Lowercase Letters: " + hasLowercase);
        System.out.println("Contains Numbers: " + hasNumber);
        System.out.println("Contains Special Characters: " + hasSpecialChar);

        int score = calculateScore(length, hasUppercase, hasLowercase, hasNumber, hasSpecialChar);
        System.out.println("Strength Score: " + score);
        System.out.println("Feedback: " + getFeedback(score));

        sc.close();
    }

    public static int calculateScore(int length, boolean hasUppercase, boolean hasLowercase, boolean hasNumber, boolean hasSpecialChar) {
        int score = 0;

        if (length >= 8) {
            score += 3;
        } else if (length >= 6) {
            score += 2;
        } else {
            score += 1;
        }

        if (hasUppercase && hasLowercase) {
            score += 2;
        }

        if (hasNumber) {
            score += 1;
        }

        if (hasSpecialChar) {
            score += 2;
        }

        return score;
    }

    public static String getFeedback(int score) {
        if (score >= 8) {
            return "Very Strong";
        } else if (score >= 6) {
            return "Strong";
        } else if (score >= 4) {
            return "Moderate";
        } else if (score >= 2) {
            return "Weak";
        } else {
            return "Very Weak";
        }
    }
}