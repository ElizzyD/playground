import java.util.Scanner;

/**
 * A tic-tac-toe game.
 */
public class TicTacToe {

    private static int[][] threeInARow = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    /**
     * Prints a tic-tac-toe board.
     *
     * @param board The board to print.
     */
    private static void printBoard(char[][] board) {
        System.out.println();

        for (int i = 0; i < board.length; i++) {
            printRow(board[i]);

            System.out.println();

            if (i < (board.length - 1)) {
                printSeparator(board[i].length);
            }
        }
    }

    /**
     * Prints a row of the tic-tac-toe board.
     *
     * @param row
     */
    private static void printRow(char[] row) {
        for (int i = 0; i < row.length; i++) {
            System.out.print(" " + row[i] + " ");
            if (i < (row.length - 1)) {
                System.out.print("|");
            }
        }
    }

    /**
     * Prints the separator line between tic-tac-toe rows.
     *
     * @param numSquares
     */
    private static void printSeparator(int numSquares) {
        for (int i = 0; i < numSquares; i++) {
            System.out.print("---");
            if (i < (numSquares - 1)) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    /**
     * Finds if anyone has won the game yet.
     *
     * @param board
     * @return The winning player ('x' or 'o') or the space character if no one has won yet.
     */
    private static char findWinner(char[][] board) {
        // Look through every possible winning combination of spaces
        for (int i = 0; i < threeInARow.length; i++) {
            char c1 = getSpace(board, threeInARow[i][0]);
            char c2 = getSpace(board, threeInARow[i][1]);
            char c3 = getSpace(board, threeInARow[i][2]);

            // If the same player has all 3, they won!
            if (c1 == c2 && c2 == c3 && c3 != ' ') {
                return c3;
            }
        }

        // No one won yet
        return ' ';
    }

    /**
     * Converts the spaceNumber into the row index into the board 2d array.
     *
     * @param spaceNum
     * @param rowLength
     * @return row index
     */
    private static int getRow(int spaceNum, int rowLength) {
        return spaceNum / rowLength;
    }

    /**
     * Converts the spaceNumber into the column index into the board 2d array.
     *
     * @param spaceNum
     * @param rowLength
     * @return column index
     */
    private static int getCol(int spaceNum, int rowLength) {
        return spaceNum % rowLength;
    }

    /**
     * Gets the current symbol in the board at the given space number.
     *
     * @param board
     * @param spaceNum
     * @return symbol in that space number.
     */
    private static char getSpace(char[][] board, int spaceNum) {
        int r = getRow(spaceNum, board[0].length);
        int c = getCol(spaceNum, board[0].length);

        return board[r][c];
    }

    /**
     * Updates the symbol in the board at the given space number.
     *
     * @param board
     * @param spaceNum
     * @param symbol
     */
    private static void updateBoardSpot(char[][] board, int spaceNum, char symbol) {
        int r = getRow(spaceNum, board[0].length);
        int c = getCol(spaceNum, board[0].length);

        board[r][c] = symbol;
    }

    /**
     * Reads the player's desired space number on the board. Will loop until the user gives a valid selection.
     *
     * @param board
     * @param keyboard
     * @param currentPlayer
     * @return space number desired by the user
     */
    private static int promptUserForSelection(char[][] board, Scanner keyboard, char currentPlayer) {
        int spaceNum = -1;
        boolean validSpace = false;

        while (!validSpace) {
            System.out.println();
            System.out.print("Current player is '" + currentPlayer + "', enter a space: ");
            spaceNum = keyboard.nextInt();

            validSpace = (spaceNum >= 0 && spaceNum <= 8 && getSpace(board, spaceNum) == ' ');
        }

        return spaceNum;
    }

    /**
     * Prints the winner of the game.
     *
     * @param winner
     */
    private static void printWinner(char winner) {
        System.out.println();

        if (winner != ' ') {
            System.out.println(winner + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    /**
     * Checks if the game is over. The game is over if either 1) a player won or 2) all spaces are taken.
     *
     * @param board
     * @param winner
     * @return true if the game is over, false, otherwise
     */
    private static boolean isGameOver(char[][] board, char winner) {
        // If someone won, the game is over
        if (winner != ' ') {
            return true;
        } else {
            // Otherwise, look for any empty spaces
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    // If there's an empty space, the game is still on
                    if (board[i][j] == ' ') {
                        return false;
                    }
                }
            }

            // All spaces taken
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        char winner = ' ';
        char currentPlayer = 'o';
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

        printBoard(board);

        while (!isGameOver(board, winner)) {
            int spaceNum = promptUserForSelection(board, keyboard, currentPlayer);
            updateBoardSpot(board, spaceNum, currentPlayer);
            printBoard(board);

            currentPlayer = (currentPlayer == 'o' ? 'x' : 'o');
            winner = findWinner(board);
        }

        printWinner(winner);
    }
}
