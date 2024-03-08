import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            playGame(sc);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainChoice = sc.next();
            playAgain = playAgainChoice.equalsIgnoreCase("yes");
        }
        sc.close();
    }

    public static void playGame(Scanner scanner) {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        char currentPlayer = 'X';
        boolean gameWon = false;
        int totalMoves = 0;

        while (!gameWon && totalMoves < 9) {
            displayBoard(board);
            int[] move = getPlayerMove(scanner, currentPlayer);
            int row = move[0];
            int col = move[1];

            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                gameWon = checkWin(board, currentPlayer);
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                totalMoves++;
            } else {
                System.out.println("Invalid move! Cell already occupied. Try again.");
            }
        }

        displayBoard(board);

        if (gameWon) {
            char winner = (currentPlayer == 'X') ? 'O' : 'X';
            System.out.println("Player " + winner + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    public static void displayBoard(char[][] board) {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] getPlayerMove(Scanner scanner, char player) {
        int[] move = new int[2];
        System.out.print("Player " + player + ", enter your move (row column): ");
        move[0] = scanner.nextInt();
        move[1] = scanner.nextInt();
        return move;
    }

    public static boolean checkWin(char[][] board, char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }
}
