import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        double p = (a + b + c) / 2.0;

        double square = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        System.out.println(square);
    }
}