import java.math.BigInteger;
import java.util.Scanner;

class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BigInteger usersNumber = scanner.nextBigInteger();
        BigInteger factorial = BigInteger.ONE;
        int i = 1;
        while (factorial.compareTo(usersNumber) < 0) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
            i++;
        }

        System.out.println(i - 1);
    }
}