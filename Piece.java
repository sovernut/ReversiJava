class Piece {
    private String pieceName;
    private char pieceSymbol;
    Piece(String pieceName){
        this.pieceName = pieceName;
        this.pieceSymbol = pieceName.charAt(0);
    }

    public String getName(){
        return this.pieceName;
    }

    public char getSymbol(){
        return this.pieceSymbol;
    }
}