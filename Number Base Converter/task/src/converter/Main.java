package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final String[] HEX_LETTERS = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static final String START_MESSAGE =
            "Enter two numbers in format: {source base} {target base} (To quit type /exit)";

    private static final String ITERATION_MESSAGE =
            "Enter number in base %d to convert to base %d (To go back type /back)";

    private static final String[] commands = {"/exit"};
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        boolean exit = false;
        while (!exit) {
            int sourceBase, targetBase;

            System.out.println(START_MESSAGE);
            String input = scanner.next();
            if (input.equals("/exit")) {
                break;
            } else {
                sourceBase = Integer.parseInt(input);
            }

            input = scanner.next();
            if (input.equals("/exit")) {
                break;
            } else {
                targetBase = Integer.parseInt(input);
            }

            exit = convertationStage(sourceBase, targetBase);
        }

    }

    public static boolean convertationStage(int sourceBase, int targetBase) {
        do {
            System.out.printf(ITERATION_MESSAGE, sourceBase, targetBase);
            System.out.println();
            String sourceNumber = scanner.next();
            if (sourceNumber.equals("/exit")) {
                return true;
            } else if (sourceNumber.equals("/back")) {
                return false;
            } else {
                String convertationResult = anyBaseConvert(sourceBase, targetBase, sourceNumber);
                System.out.println("Conversion result: " + convertationResult);
            }

        } while (true);
    }

    public static String anyBaseConvert(int sourceBase, int targetBase, String sourceNumber) {
        String[] partsOfNumber = sourceNumber.split("[.]");
        if (partsOfNumber.length < 2) {
            String decimalResult = convertToDecimal(sourceNumber, sourceBase);
            String targetResult = convertFromDecimal(new BigInteger(decimalResult, 10), targetBase);

            return targetResult;
        } else {
            String decimalIntegerResult = convertToDecimal(partsOfNumber[0], sourceBase);
            String integerResult = convertFromDecimal(new BigInteger(decimalIntegerResult, 10), targetBase);

            String decimalFractionalResult = convertFractionalPartToDecimal(partsOfNumber[1], sourceBase);
            String fractionalResult = convertFractionalFromDecimal(decimalFractionalResult, targetBase);
            return integerResult + "." + fractionalResult;
        }
    }

    private static String convertFractionalFromDecimal(String decimalFractionalResult, int targetBase) {
        StringBuilder converted = new StringBuilder();
        BigDecimal quotient = new BigDecimal("." + decimalFractionalResult);
        String[] quotientParts;
        do {
            quotient = quotient.multiply(BigDecimal.valueOf(targetBase));
            quotientParts = quotient.toString().split("[.]");
            converted.append(getDigit(new BigInteger(quotientParts[0])));
            quotient = new BigDecimal("." + quotientParts[1]);
        } while (quotientParts.length > 1 && quotientParts[1] != "0" && converted.length() < 5);

        return converted.toString();
    }

    private static String convertFractionalPartToDecimal(String number, int sourceBase) {
        String[] digits = number.split("");
        BigDecimal fractional = BigDecimal.ZERO;
        for (int i = 0; i < digits.length; i++) {
            fractional = fractional.add(BigDecimal.valueOf(getDecimalDigit(digits[i]) * Math.pow(sourceBase, -(i + 1))));
        }

        return fractional.toString().split("[.]")[1];
    }

    public static String convertToDecimal(String number, int numberBase) {
        String[] reversedNumber = new StringBuilder(number).reverse().toString().split("");

        BigInteger decimal = BigInteger.ZERO;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(BigInteger.valueOf(reversedNumber.length)) < 0; i = i.add(BigInteger.ONE)) {
            decimal = decimal.add(BigInteger.valueOf((long) (getDecimalDigit(reversedNumber[i.intValue()]) * Math.pow(numberBase, i.intValue()))));
        }

        return decimal.toString();
    }

    public static String convertFromDecimal(BigInteger decimal, int base) {
        StringBuilder converted = new StringBuilder();
        BigInteger quotient = decimal;

        do {
            BigInteger reminder = quotient.remainder(BigInteger.valueOf(base));
            quotient = quotient.divide(BigInteger.valueOf(base));
            converted.insert(0, getDigit(reminder));
        } while (quotient.compareTo(BigInteger.valueOf(base).subtract(BigInteger.ONE)) == 1);

        converted.insert(0, getDigit(quotient));

        final String regex = "^0+";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        return pattern.matcher(converted.toString()).replaceAll("");
    }

    public static String getDigit(BigInteger digit) {
        if (digit.compareTo(BigInteger.valueOf(9)) == 1 && digit.compareTo(BigInteger.valueOf(36)) <= 0) {
            return HEX_LETTERS[digit.intValue() - 10];
        } else {
            return digit.toString();
        }
    }

    public static int getDecimalDigit(String sourceDigit) {
        final Map<String, Integer> hexMap = new HashMap<>();
        for (int i = 0; i < HEX_LETTERS.length; i++) {
            hexMap.put(HEX_LETTERS[i], i + 10);
        }

        if (hexMap.containsKey(sourceDigit.toUpperCase())) {
            return hexMap.get(sourceDigit.toUpperCase());
        } else {
            return Integer.parseInt(sourceDigit);
        }
    }
}