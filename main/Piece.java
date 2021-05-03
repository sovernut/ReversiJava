package main;

public class Piece {
    private String pieceName;
    private char pieceSymbol;

    Piece(String pieceName) {
        this.pieceName = pieceName;
        this.pieceSymbol = pieceName.charAt(0);
    }

    Piece(String pieceName, char sym) {
        this.pieceName = pieceName;
        this.pieceSymbol = sym;
    }

    public String getName() {
        return this.pieceName;
    }

    public void setSymbol(char sym) {
        this.pieceSymbol = sym;
    }

    public char getSymbol() {
        return this.pieceSymbol;
    }
}