import java.util.Scanner;


class Reversi {
    static Board board;
    static boolean gameEnd;
    static boolean isWhiteTurn;
    public static void main(String args[]){
        System.out.println(" ::: Welcome To Reversi Game ::: ");
        board = new Board("Reversi",8,"black","white");
        Scanner in = new Scanner(System.in);
        while (!gameEnd){
            String s = in.nextLine();
            String[] inRowCol = s.split(",");
            int row = Integer.parseInt(inRowCol[0]);
            int col = Integer.parseInt(inRowCol[1]);
            placePiece(row,col);
            if(row == 99) gameEnd = true;
            board.show();
        }
        in.close();
    }

    static void placePiece(int row, int col){
        if(isWhiteTurn){
            board.place("white", row, col);
        } else {
            board.place("black", row, col);
        }
        isWhiteTurn = !isWhiteTurn;
    }
}