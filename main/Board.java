package main;

import action.IAction;
import action.DoNothing;

class Board {
    String boardName;
    Piece[][] board;
    String[][] marker;
    int size;
    Piece aPiece;
    Piece bPiece;
    IAction bAction;

    Board(String name, int size, String aName, String bName) {
        this.boardName = name;
        this.size = size;
        this.board = new Piece[size][size];
        this.marker = new String[size][size];
        this.aPiece = new Piece(aName);
        this.bPiece = new Piece(bName);
        this.setBoardAction(new DoNothing());
        System.out.println(boardName + "( Size: " + size + " ) Board Initialized.");
    }

    void setBoardAction(IAction bAction) {
        this.bAction = bAction;
    }

    void show() {
        System.out.print("   ");
        for (int i = 0; i < board.length; i++)
            System.out.print(" " + (i + 1) + " ");
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(" " + (i + 1) + " ");
            for (int k = 0; k < board[i].length; k++) {
                if (board[i][k] != null)
                    System.out.print(" " + board[i][k].getSymbol() + " ");
                else if (marker[i][k] != null)
                    System.out.print(" " + marker[i][k] + " ");
                else
                    System.out.print("   ");
            }
            System.out.println();
        }
    }

    boolean place(String pieceName, int row, int col) {
        row -= 1;
        col -= 1;
        if (!isCellPlaced(row, col) && isInBoard(row, col)) {
            board[row][col] = aPiece.getName() == pieceName ? aPiece : bPiece;
            return true;
        }
        return false;
    }

    boolean placeWithExactIndex(String pieceName, int row, int col) {
        if (isInBoard(row, col)) {
            board[row][col] = aPiece.getName() == pieceName ? aPiece : bPiece;
            return true;
        }
        return false;
    }

    boolean placeWithAction(String pieceName, int row, int col) {
        boolean hasPlace = place(pieceName, row, col);
        if (hasPlace) {
            this.bAction.Action(board);
        }
        return hasPlace;
    }

    void setMarker(String[][] marker) {
        this.marker = marker;
    }

    public boolean isInBoard(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    public boolean isCellPlaced(int row, int col) {
        return board[row][col] != null;
    }

    Piece[][] getBoard() {
        return board;
    }

    String getPieceName(int row, int col) {
        if ((isInBoard(row, col) && isCellPlaced(row, col)))
            return board[row][col].getName();
        return "OUT_BOUND";
    }
}