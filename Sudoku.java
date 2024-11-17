import java.util.Random;
import java.util.Scanner;

public class Sudoku {
    private static final int[][] pattern = {
        {1, 1, 0, 0, 1, 0, 0, 0, 0},
        {1, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 0, 0, 0, 1, 0},
        {1, 0, 0, 0, 1, 0, 0, 0, 1},
        {1, 0, 0, 1, 0, 1, 0, 0, 1},
        {1, 0, 0, 0, 1, 0, 0, 0, 1},
        {0, 1, 0, 0, 0, 0, 1, 1, 0},
        {0, 0, 0, 1, 1, 1, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 0, 1, 1}
    };

    private static int[][] generateBoard() {
        int[][] board = new int[9][9];
        Random random = new Random();
        int[] numbers = randomizeNumbers();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (pattern[row][col] == 1) {
                    board[row][col] = numbers[random.nextInt(9)];
                } else {
                    board[row][col] = 0; // Empty cell
                }
            }
        }

        return board;
    }

    private static int[] randomizeNumbers() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random random = new Random();

        for (int i = numbers.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        return numbers;
    }

    private static void displayBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }

        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] board = generateBoard();

        System.out.println("Welcome to Sudoku!");
        while (true) {
            System.out.println("\nCurrent Board:");
            displayBoard(board);

            System.out.println("\nEnter your move in the format 'row col num' (e.g., 1 1 5). Enter '-1' to quit:");
            String input = scanner.nextLine();
            if (input.equals("-1")) {
                System.out.println("Thank You");
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 3) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            int row, col, num;
            try {
                row = Integer.parseInt(parts[0]) - 1;
                col = Integer.parseInt(parts[1]) - 1;
                num = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please use numbers only.");
                continue;
            }

            if (row < 0 || row >= 9 || col < 0 || col >= 9 || num < 1 || num > 9) {
                System.out.println("Invalid move. Ensure row and col are between 1-9 and num is between 1-9.");
                continue;
            }

            if (board[row][col] != 0) {
                System.out.println("Cell already filled. Choose an empty cell.");
                continue;
            }

            if (!isValid(board, row, col, num)) {
                System.out.println("Invalid move. It violates Sudoku rules.");
                continue;
            }

            board[row][col] = num;
            System.out.println("Move accepted!");
        }

        scanner.close();
    }
}


