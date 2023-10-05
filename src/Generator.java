import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Generator {

    public static final String NORM_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String SPECIAL_CHARS = "@%+\\/'!#$^?:,(){}[]~-_.";

    public Generator() {
    }

    public static void printInit() {
        System.out.println(".....");
    }

    public static void main(String[] str) {
        Scanner scanner = new Scanner(System.in);
        boolean passwordGenerated = false;
        boolean specialChars = false;
        boolean error = false;

        menu();

        while (scanner.hasNextLine()) {
            int length = 0;
            if (error) {
                break;
            }

            if (scanner.hasNextInt()) {
                // input was an integer
                length = Integer.parseInt(scanner.nextLine());

                while (length < 8 || length > 18) {
                    // password needs to be longer.
                    // but also need to verify that the user puts in an int
                    System.out.println("Please put in an appropriate length.");
                    String opt = scanner.nextLine();

                    if (!scanner.hasNextInt()) {
                        continue;
                    }

                    opt = scanner.nextLine();
                    if (Integer.parseInt(opt) >= 8 && Integer.parseInt(opt) <= 18) {
                        length = Integer.parseInt(opt);
                    }
                }
            } else {
                // input was not an integer
                length = validateLength(0);
            }

            System.out.println(".");
            System.out.println(".   .");
            System.out.println(".   .   .");
            System.out.println("Your password will be " + length + " characters long.");

            System.out.println("Do you want special characters?" + "/n" + "I.e.: !@#$");
            System.out.println("Please respond with 'Yes' or 'No'");

            String secondResponse = scanner.nextLine();

            if (secondResponse.toLowerCase().equals("yes")) {
                specialChars = true;
            }

            generatePassword(length, specialChars);

            System.out.println("You're welcome.");

            System.exit(0);

        }

        scanner.close();
    }

    private static void menu() {
        System.out.println("Welcome to my Password Generator.");
        System.out.println("Here I'll ask you a few questions that will help me to produce a good password for you.");
        System.out.println(". . . . . .");
        System.out.println(". . . . . .");
        System.out.println(". . . . . .");
        System.out.println("How long do you want your password?");
        System.out.println("I would suggest you pick something between 8 and 18 characters long. The longer the better.");
    }

    private static int validateLength(int length) {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt() && length == 0) {
            // this method should trigger if the user DOES NOT put in an integer
            System.out.println("Please put in an integer and make sure that it is between 8 and 18.");
            String num = scanner.nextLine();

            if (!scanner.hasNextInt()) {
                continue;
            }

            // reassign value here BC otherwise it will go with previously chosen value
            num = scanner.nextLine();
            if (Integer.parseInt(num) > 8 && Integer.parseInt(num) < 18) {
                length = Integer.parseInt(num);
                break;
            }

        }

        return length;
    }

    private static String generatePassword(int length, boolean specialChars) {

        StringBuilder str = new StringBuilder("");
        Random rando = new Random();
        StringBuilder password = new StringBuilder("");
        int passwordLength = length - 1;

        str.append(NORM_CHARS);

        if (specialChars) {
            str.append(SPECIAL_CHARS);
        }

        for (int i = 0; i < length; i++) {
            int select = rando.nextInt(str.length());
            password.append(str.toString().charAt(select));
        }

        System.out.println("Your Password is: " + password.toString());

        return password.toString();

    }

}
