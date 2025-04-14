package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // write your code here
        char[][] board = drawInitialBoard();
        boolean xTurn = true;
        boolean oTurn = !xTurn;
        char piece;
        while (true) {
            int coordX = 0;
            int coordY = 0;
            if (xTurn) {
                piece = 'X';
            } else {
                piece = 'O';
            }
            System.out.println("Make a move for " + piece + ": provide x y coords.");
            System.out.println("(1 1) (1 2) (1 3)");
            System.out.println("(2 1) (2 2) (2 3)");
            System.out.println("(3 1) (3 2) (3 3)");
            while (true) {
                if (scanner.hasNextInt()) {
                    coordX = scanner.nextInt();
                } else {
                    System.out.println("You should enter numbers!");
                    scanner.next();
                    continue;
                }
                if (scanner.hasNextInt()) {
                    coordY = scanner.nextInt();
                } else {
                    System.out.println("You should enter numbers!");
                    scanner.next();
                    continue;
                }
                if (!isValidCoord(coordX, coordY)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                if (!isEmptySpace(board, coordX, coordY)) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
                break;
            }
            board = updateBoard(board, coordX, coordY, piece);
            drawUpdatedBoard(board);
            xTurn = !xTurn;
            boolean xWins = checkRow(board, 'X') || checkColumn(board, 'X') || checkDiagonals(board, 'X');
            boolean oWins = checkRow(board, 'O') || checkColumn(board, 'O') || checkDiagonals(board, 'O');
            boolean impossible = isImpossible(board) || (xWins && oWins);

            if (impossible){
                System.out.println("Impossible");
                break;
            } else if (xWins) {
                System.out.println("X wins");
                break;
            } else if (oWins) {
                System.out.println("O wins");
                break;
            } else if (!gameComplete(board)) {

            } else {
                System.out.println("Draw");
                break;
            }
        }
        scanner.close();
    }

    private static char[][] drawInitialBoard() {
        char[][] board = new char[3][3];
        System.out.println("---------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '_';
                System.out.print(" " + board[i][j]);
            }
            System.out.print(" |");
            System.out.println();
        }
        System.out.println("---------");
        return board;
    }

    private static void drawUpdatedBoard(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.print(" |");
            System.out.println();
        }
        System.out.println("---------");
    }

    private static char[][] updateBoard(char[][] board, int coordX, int coordY, char insert) {
        board[coordX - 1][coordY - 1] = insert;
        return board;
    }

    private static boolean isValidCoord(int coordX, int coordY) {
        int[] valid =  {1, 2, 3};
        boolean xValid = false;
        boolean yValid = false;
        for (int i : valid) {
            if (coordX == i) {
                xValid = true;
                break;
            }
        }
        for (int i : valid) {
            if (coordY == i) {
                yValid = true;
            }
        }
        return xValid && yValid;
    }

    private static boolean isEmptySpace(char[][] board, int coordX, int coordY) {
        if (board[coordX-1][coordY-1] == '_') {
            return true;
        } else {
            return false;
        }
    }

    private static boolean gameComplete(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkRow(char[][] board, char target) {
         return (board[0][0] == target && board[0][1] == target && board[0][2] == target) ||
                 (board[1][0] == target && board[1][1] == target && board[1][2] == target) ||
                 (board[2][0] == target && board[2][1] == target && board[2][2] == target);
    }

    private static boolean checkColumn(char[][] board, char target) {
        return (board[0][0] == target && board[1][0] == target && board[2][0] == target) ||
                (board[0][1] == target && board[1][1] == target && board[2][1] == target) ||
                (board[0][2] == target && board[1][2] == target && board[2][2] == target);
    }

    private static boolean checkDiagonals(char[][] board, char target) {
        return (board[0][0] == target && board[1][1] == target && board[2][2] == target) ||
                (board[0][2] == target && board[1][1] == target && board[2][0] == target);
    }

    private static boolean isImpossible(char[][] board) {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    countX++;
                } else if (board[i][j] == 'O') {
                    countO++;
                }
            }
        }
        return (countX - countO >= 2 || countO - countX >= 2);
    }
}
