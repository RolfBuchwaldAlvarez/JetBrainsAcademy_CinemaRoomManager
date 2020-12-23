import java.util.Scanner;

class FixingArithmeticException {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();
        int result = 0;

        if (d == 0 || (b + c) == 0) {
            System.out.println("Division by zero!");
        } else {
            result = a / ((b + c) / d);
            System.out.println(result);
        }
    }
}