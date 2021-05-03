package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Reversi {
    static Board board;
    static boolean gameEnd;
    static boolean isWhiteTurn;
    static int blackScore;
    static int whiteScore;
    static String[][] markers;

    public static void main(String args[]) {
        System.out.println(" ::: Welcome To Reversi Game ::: ");
        board = new Board("Reversi", Constant.BOARD_SIZE, Constant.WHITE_NAME, Constant.BLACK_NAME);
        markers = new String[Constant.BOARD_SIZE][Constant.BOARD_SIZE];
        Scanner in = new Scanner(System.in);
        board.place(Constant.BLACK_NAME, 4, 4);
        board.place(Constant.BLACK_NAME, 5, 5);
        board.place(Constant.WHITE_NAME, 4, 5);
        board.place(Constant.WHITE_NAME, 5, 4);
        try {
            while (!gameEnd) {
                markers = generateMarkerForTurn(getTurnName());
                board.setMarker(markers);
                board.show();
                System.out.print(getTurnName() + " >> ");

                String s = in.nextLine();
                String[] inRowCol = s.split(" ");
                int row = Integer.parseInt(inRowCol[0]);
                int col = Integer.parseInt(inRowCol[1]);
                placePiece(row, col);

                countScore();
            }
        } catch (Exception e) {
            System.out.println("Invalid arguments.");
            e.printStackTrace();
        } finally {
            in.close();
        }

    }

    static String getTurnName() {
        return isWhiteTurn ? Constant.WHITE_NAME : Constant.BLACK_NAME;
    }

    static void toggleTurn() {
        isWhiteTurn = !isWhiteTurn;
    }

    static String getEnemyTurnName(String turn) {
        return turn == Constant.WHITE_NAME ? Constant.BLACK_NAME : Constant.WHITE_NAME;
    }

    static void placePiece(int row, int col) {
        if (!board.isInBoard(row, col))
            return;
        if (markers[row - 1][col - 1] != Constant.MARKER)
            return;
        String turn = getTurnName();
        if (board.place(turn, row, col)) {
            eatWith(turn, row, col);
            toggleTurn();
        }
    }

    static void countScore() {
        blackScore = 0;
        whiteScore = 0;
        Piece[][] boards = board.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boards[i][j] != null) {
                    if (boards[i][j].getSymbol() == 'b') {
                        blackScore++;
                    } else {
                        whiteScore++;
                    }
                }
            }
        }
        System.out.println("   Black : " + blackScore + " || White : " + whiteScore);
    }

    static String[][] generateMarkerForTurn(String turn) {
        String[][] marker = new String[Constant.BOARD_SIZE][Constant.BOARD_SIZE];
        Piece[][] c_board = board.getBoard();
        String enemy = getEnemyTurnName(turn);
        for (int i = 0; i < Constant.BOARD_SIZE; i++) {
            for (int k = 0; k < Constant.BOARD_SIZE; k++) {
                if (c_board[i][k] != null && c_board[i][k].getName().equals(enemy)) {
                    markPlaceable(marker, turn, i, k);
                }
            }
        }
        return marker;
    }

    static void markPlaceable(String[][] marker, String turn, int x_start, int y_start) {
        int x[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int y[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
        for (int i = 0; i < x.length; i++) {
            boolean isInBoard = board.isInBoard(x[i] + x_start, y[i] + y_start);
            boolean isAllyPieceOpposite = isPieceOpposite(turn, x_start + x[i] * -1, y_start + y[i] * -1, x[i] * -1,
                    y[i] * -1);
            if (isInBoard && isAllyPieceOpposite && !board.isCellPlaced(x[i] + x_start, y[i] + y_start)) {
                marker[x[i] + x_start][y[i] + y_start] = Constant.MARKER;
            }
        }
    }

    static void eatWith(String turn, int row, int col) {
        row -= 1;
        col -= 1;
        int x[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int y[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
        String enemy = getEnemyTurnName(turn);
        for (int i = 0; i < x.length; i++) {
            int row_t = row + y[i];
            int col_t = col + x[i];
            while (board.getPieceName(row_t, col_t).equals(enemy)) {
                row_t += y[i];
                col_t += x[i];
            }
            if (!board.getPieceName(row_t, col_t).equals(turn)) continue;

            row_t = row + y[i];
            col_t = col + x[i];
            while (board.getPieceName(row_t, col_t).equals(enemy)) {
                board.placeWithExactIndex(turn, row_t, col_t);
                row_t += y[i];
                col_t += x[i];
            }

        }
    }

    static boolean isPieceOpposite(String turn, int x, int y, int x_dir, int y_dir) {
        Piece[][] c_board = board.getBoard();
        while (board.isInBoard(x, y) && board.isCellPlaced(x, y)) {
            if (c_board[x][y].getName().equals(turn)) {
                return true;
            }
            x += x_dir;
            y += y_dir;
        }
        return false;
    }
}