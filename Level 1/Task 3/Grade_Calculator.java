import java.util.Scanner;

public class Grade_Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Prompt the user to enter name
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println();

        // Prompt the user to enter the number of Subjects
        System.out.print("Enter the number of Subjects: ");
        int numberOfSubjects = sc.nextInt();

        // Input each grade
        int[] grades = new int[numberOfSubjects];
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter grade of subject " + (i + 1) + ": ");
            grades[i] = sc.nextInt();
        }

        // Calculate the sum of grades
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }

        // Calculate the average grade
        double average = (double) sum / numberOfSubjects;

        // Display the average grade in two decimal places
        System.out.println();
        System.out.println(name + " your average grade is: " + String.format("%.2f", average));

        sc.close();
    }
}

