import java.util.Scanner;


class Reversi {
    static Board board;
    static boolean gameEnd;
    static boolean isWhiteTurn;
    public static void main(String args[]){
        System.out.println(" ::: Welcome To Reversi Game ::: ");
        board = new Board("Reversi",8,"black","white");
        board.show();
        Scanner in = new Scanner(System.in);
        while (!gameEnd){
            String s = in.nextLine();
            String[] inRowCol = s.split(",");
            int row = Integer.parseInt(inRowCol[0]);
            int col = Integer.parseInt(inRowCol[1]);
            System.out.println(row+","+col);
            if(row == 99) gameEnd = true;
        }
        in.close();
    }

    void placePiece(int row, int col){
        if(isWhiteTurn){
            board.place("white", row, col);
        } else {
            board.place("black", row, col);
        }
        isWhiteTurn = !isWhiteTurn;
    }
}