package tictactoe;

import java.util.Scanner;

public class Main {

    final static int boardSize = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] board = drawBoard(null, true);
        boolean isXTurn = true;
        boolean isOTurn = !isXTurn;
        char piece;
        while (true) {
            int coordX;
            int coordY;
            piece = (isXTurn ? 'X' : 'O');
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
            drawBoard(board, false);
            isXTurn = !isXTurn;
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

    private static char[][] drawBoard(char[][] board, boolean initialize) {
        if (initialize) {
            board = new char[boardSize][boardSize];
        }
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                if (initialize) {
                    board[i][j] = '_';
                }
                System.out.print(" " + board[i][j]);
            }
            System.out.print(" |");
            System.out.println();
        }
        System.out.println("---------");
        return board;
    }

    private static char[][] updateBoard(char[][] board, int coordX, int coordY, char insert) {
        board[coordX - 1][coordY - 1] = insert;
        return board;
    }

    private static boolean isValidCoord(int coordX, int coordY) {
        return coordX >= 1 && coordX <= boardSize && coordY >= 1 && coordY <= boardSize;
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

    private static boolean checkRow(char[][] board, char player) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumn(char[][] board, char player) {
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals(char[][] board, char player) {
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
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
