class Board {
    String boardName; 
    Piece[][] board;
    Piece aPiece;
    Piece bPiece;
    Board(String name,int size,String aName,String bName) {
        this.boardName = name;
        this.board = new Piece[size][size];
        this.aPiece = new Piece(aName);
        this.bPiece = new Piece(bName);
        System.out.println(boardName + "( Size: " + size + " ) Board Initialized.");
    }

    void show(){
        for (int i = 0;i< board.length;i++){
            for (int k = 0;k< board[i].length;k++){
                if (board[i][k] != null) System.out.print(" "+board[i][k].getSymbol()+" ");
                else System.out.print(" - ");
            }
            System.out.println();
        }
    }

    void place(String pieceName, int row, int col){
        if(!isCellPlaced(row,col)){
            board[row][col] = aPiece.getName() == pieceName? aPiece : bPiece;
        }
    }

    boolean isCellPlaced(int row, int col){
        return board[row][col] == null;
    }
}