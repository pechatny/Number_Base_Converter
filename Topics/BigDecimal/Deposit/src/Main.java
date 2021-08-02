import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        BigDecimal startingAmount = scanner.nextBigDecimal();
        BigDecimal percent = scanner.nextBigDecimal();
        int years = scanner.nextInt();
        BigDecimal finalAmount = percent
                .divide(BigDecimal.valueOf(100))
                .add(BigDecimal.ONE)
                .pow(years)
                .multiply(startingAmount);

        System.out.println("Amount of money in the account: " + finalAmount.setScale(2, RoundingMode.CEILING));
    }
}