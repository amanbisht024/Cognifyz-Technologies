import java.io.*;

public class File_Encryptor_Decryptor {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Prompt the user to choose encryption or decryption
            System.out.println("Choose operation:");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.print("Enter your choice (1 or 2): ");
            int choice = Integer.parseInt(reader.readLine());

            // Prompt the user to input the file name or path
            System.out.print("Enter the file name or path: ");
            String fileName = reader.readLine();

            // Perform encryption or decryption based on the user's choice
            switch (choice) {
                case 1:
                    encryptFile(fileName);
                    break;
                case 2:
                    decryptFile(fileName);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose 1 or 2.");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }
    }

    public static void encryptFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted_" + fileName))) {
    
            int key = 3; // Shift key for Caesar cipher
    
            int character;
            while ((character = reader.read()) != -1) {
                // Encrypt each character using Caesar cipher
                if (Character.isLetter(character)) {
                    if (Character.isUpperCase(character)) {
                        character = (character - 'A' + key) % 26 + 'A'; // Uppercase letters
                    } else {
                        character = (character - 'a' + key) % 26 + 'a'; // Lowercase letters
                    }
                }
                writer.write(character);
            }
            System.out.println("File encrypted successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    

    public static void decryptFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter("decrypted_" + fileName))) {
    
            int key = 3; // Shift key for Caesar cipher
    
            int character;
            while ((character = reader.read()) != -1) {
                // Decrypt each character using Caesar cipher
                if (Character.isLetter(character)) {
                    if (Character.isUpperCase(character)) {
                        character = (character - 'A' - key + 26) % 26 + 'A'; // Uppercase letters
                    } else {
                        character = (character - 'a' - key + 26) % 26 + 'a'; // Lowercase letters
                    }
                }
                writer.write(character);
            }
            System.out.println("File decrypted successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
