import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int maxNumber = 0;
        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            if (number % 4 == 0 && number > maxNumber) {
                maxNumber = number;
            }
        }

        System.out.println(maxNumber);
    }
}