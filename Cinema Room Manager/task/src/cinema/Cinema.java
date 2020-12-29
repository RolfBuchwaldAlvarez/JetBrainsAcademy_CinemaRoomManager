package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = sc.nextInt();
        System.out.println();

        String[][] matrix;
        matrix = fillMatrix(rows, seatsPerRow);

        int selection = 9; // Random number != {0, 1, 2, 3} for initializing.
        int totalAmountOfSeats = rows * seatsPerRow;
        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = incomeCalculator(rows, seatsPerRow);

        while (selection != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            selection = sc.nextInt();
            System.out.println();

            switch (selection) {
                case 1: printSeatsInScreenRoom(matrix, rows, seatsPerRow);
                        break;
                case 2: currentIncome += bookSeatInMatrix(matrix, sc, rows, seatsPerRow);
                        purchasedTickets++;
                        break;
                case 3: showStatistics(totalAmountOfSeats, purchasedTickets, currentIncome, totalIncome);
                default: break;
            }
        }

    }

    public static int price(int rows, int seatsPerRow, int rowNum) {
        int price;
        if (rows * seatsPerRow <= 60) {
            price = 10;
        } else {
            if (rows % 2 == 0 && rowNum <= rows / 2) {
                price = 10;
            } else if (rows % 2 == 0 && rowNum > rows / 2) {
                price = 8;
            } else if (rows % 2 != 0 && rowNum <= (rows - 1) / 2) {
                price = 10;
            } else {
                price = 8;
            }
        }
        return price;
    }

    public static int incomeCalculator(int rows, int seatsPerRow) {
        int income;
        if (rows * seatsPerRow <= 60) {
            income = rows * seatsPerRow * 10;
        } else {
            if (rows % 2 == 0) {
                income = rows / 2 * seatsPerRow * 18;
            } else {
                income = ((rows - 1) / 2) * seatsPerRow * 10 + (((rows - 1) / 2) + 1) * seatsPerRow * 8;
            }
        }
        return income;
    }

    public static String[][] fillMatrix(int rows, int seatsPerRow) {
        int rowCounter = 1;
        int colCounter = 1;

        String[][] matrix = new String[rows + 1][seatsPerRow + 1];

        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seatsPerRow + 1; j++) {
                if (i == 0 && j == 0) {
                    matrix[i][j] = " ";
                } else if (j == 0) {
                    matrix[i][j] = String.format("%d", rowCounter);
                    rowCounter++;
                } else if (i == 0) {
                    matrix[i][j] = String.format("%d", colCounter);
                    colCounter++;
                } else {
                    matrix[i][j] = "S";
                }
            }
        }
        return matrix;
    }

    public static void printSeatsInScreenRoom(String[][] matrix, int rows, int seatsPerRow) {
        System.out.println("Cinema:");
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seatsPerRow + 1; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int bookSeatInMatrix(String[][] matrix, Scanner sc, int rows, int seatsPerRow) {
        System.out.println("Enter a row number:");
        int rowNum = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNum = sc.nextInt();

        if (rowNum >= matrix.length || seatNum >= matrix[0].length) {
            System.out.println();
            System.out.println("Wrong input!");
            System.out.println();
            bookSeatInMatrix (matrix, sc, rows, seatsPerRow);
            return price(rows, seatsPerRow, rowNum);
        }

        if (matrix[rowNum][seatNum].equals("B")) {
            System.out.println();
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            bookSeatInMatrix (matrix, sc, rows, seatsPerRow);
            return price(rows, seatsPerRow, rowNum);
        }

        matrix[rowNum][seatNum] = "B";
        int price = price(rows, seatsPerRow, rowNum);
        System.out.println();
        System.out.println("Ticket price: $" + price);
        System.out.println();
        return price;
    }

    public static void showStatistics(int totalAmountOfSeats, int purchasedTickets, int currentIncome, int totalIncome) {
        String percent = String.format("%.2f", (double) purchasedTickets * 100 / totalAmountOfSeats);

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println("Percentage: " + percent + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }
}