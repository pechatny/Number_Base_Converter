import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int squareStorage = 1;
        int i = 1;
        do {
            System.out.println(squareStorage);
            i++;
            squareStorage = i * i;
        } while (squareStorage <= number);
    }
}