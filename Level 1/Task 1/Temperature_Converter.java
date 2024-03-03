import java.util.Scanner;

class Temperature_Converter {
    public static void main(String[] args) {
        double temperature, convertedTemperature;
        String unit;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter temperature value:");
        temperature = sc.nextDouble();

        System.out.println("Enter temperature unit (C for Celsius, F for Fahrenheit):");
        unit = sc.next();

        if (unit.equalsIgnoreCase("C")) {
            convertedTemperature = (temperature * 9 / 5) + 32;
            System.out.println("Temperature in Fahrenheit is: " + convertedTemperature + " F");
        } else if (unit.equalsIgnoreCase("F")) {
            convertedTemperature = (temperature - 32) * 5 / 9;
            System.out.println("Temperature in Celsius is: " + convertedTemperature + " C");
        } else {
            System.out.println("Invalid unit entered. Please enter C for Celsius or F for Fahrenheit.");
        }
        sc.close();
    }
}
