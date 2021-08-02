import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int number = 0;
        int maxNumber = 0;
        do {
            number = scanner.nextInt();
            maxNumber = number > maxNumber ? number : maxNumber;
        } while (number != 0);

        System.out.print(maxNumber);
    }
}