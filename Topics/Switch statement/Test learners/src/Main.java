import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        int answer = scanner.nextInt();
        switch (answer) {
            case 1:
                System.out.println("Yes!");
                break;
            case 2:
            case 3:
            case 4:
                System.out.println("No!");
                break;
            default:
                System.out.println("Unknown number");
                break;
        }
    }
}