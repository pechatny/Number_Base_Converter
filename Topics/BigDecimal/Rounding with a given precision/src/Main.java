import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        var number = scanner.nextBigDecimal();
        var scale = scanner.nextInt();

        System.out.print(number.setScale(scale, RoundingMode.HALF_DOWN));
    }
}