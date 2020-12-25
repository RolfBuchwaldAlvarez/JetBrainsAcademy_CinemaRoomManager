import java.util.*;

class FixingStringIndexOutOfBoundsException {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        int index = scanner.nextInt();

        if (string != null && index <= string.length() - 1 && index >= 0) {
            System.out.println(string.charAt(index));
        } else {
            System.out.println("Out of bounds!");
        }

    }
}