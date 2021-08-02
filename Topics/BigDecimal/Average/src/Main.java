import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var sum = BigDecimal.ZERO.setScale(0);
        for (int i = 0; i < 3; i++) {
            sum = sum.add(scanner.nextBigDecimal());
        }

        System.out.println(sum.divide(BigDecimal.valueOf(3), 0, RoundingMode.DOWN));
    }
}