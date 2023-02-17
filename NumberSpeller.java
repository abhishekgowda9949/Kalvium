import java.util.*;
public class NumberSpeller {
    private static final String[] ONES = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] TEENS = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public static String spellOutNumber(int number) {
        if (number < 0 || number > 9999) {
            throw new IllegalArgumentException("Number must be between 0 and 9999 inclusive");
        }
        String spelledOutNumber;
        if (number == 0) {
            spelledOutNumber = "zero";
        } else if (number < 10) {
            spelledOutNumber = ONES[number];
        } else if (number < 20) {
            spelledOutNumber = TEENS[number - 10];
        } else if (number < 100) {
            spelledOutNumber = TENS[number / 10] + (number % 10 != 0 ? "-" + ONES[number % 10] : "");
        } else if (number < 1000) {
            spelledOutNumber = ONES[number / 100] + " hundred" + (number % 100 != 0 ? " " + spellOutNumber(number % 100) : "");
        } else {
            int thousands = number / 100;
            int rest = number % 100;
            spelledOutNumber = spellOutNumber(thousands) + " hundred" + (rest != 0 ? " " + spellOutNumber(rest) : "");
        }
        if (spelledOutNumber.startsWith("zero")) {
            return spelledOutNumber;
        } else {
            if (Character.isLowerCase(spelledOutNumber.charAt(0))) {
                spelledOutNumber = Character.toUpperCase(spelledOutNumber.charAt(0)) + spelledOutNumber.substring(1);
            }
            return spelledOutNumber;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int input = sc.nextInt();
        String output = spellOutNumber(input);
        System.out.println(input + " -> " + output);
    }
}