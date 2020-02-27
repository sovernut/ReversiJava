class Board {
    String boardName; 
    Piece[][] board;
    String[][] marker;
    int size;
    Piece aPiece;
    Piece bPiece;
    Board(String name,int size,String aName,String bName) {
        this.boardName = name;
        this.size = size;
        this.board = new Piece[size][size];
        this.aPiece = new Piece(aName);
        this.bPiece = new Piece(bName);
        System.out.println(boardName + "( Size: " + size + " ) Board Initialized.");
    }

    void show(){
        System.out.print("   ");
        for (int i = 0;i< board.length;i++) System.out.print(" "+(i+1)+" ");
        System.out.println();
        for (int i = 0;i< board.length;i++){
            System.out.print(" "+(i+1)+" ");
            for (int k = 0;k< board[i].length;k++){
                if (board[i][k] != null) System.out.print(" "+board[i][k].getSymbol()+" ");
                else if (marker[i][k] != null) System.out.print(" "+marker[i][k]+" ");
                else System.out.print(" - ");
            }
            System.out.println();
        }
    }

    boolean place(String pieceName, int row, int col){
        row -= 1;
        col -= 1;
        if(!isCellPlaced(row,col) && isInBoard(row,col)){
            board[row][col] = aPiece.getName() == pieceName? new Piece(aPiece.getName()) : new Piece(bPiece.getName());
            return true;
        }
        return false;
    }

    void setMarker(String[][] marker){
        this.marker = marker;
    }

    boolean isInBoard(int row, int col){
        return row >= 0 && row < size && col >= 0 && col < size;
    }
    
    boolean isCellPlaced(int row, int col){
        return board[row][col] != null;
    }
    Piece[][] getBoard(){
        return this.board;
    }
}