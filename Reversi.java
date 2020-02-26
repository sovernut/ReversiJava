import java.util.Scanner;


class Reversi {
    static Board board;
    static boolean gameEnd;
    static boolean isWhiteTurn;
    static final String WHITE_NAME = "white";
    static final String BLACK_NAME = "black";
    public static void main(String args[]){
        System.out.println(" ::: Welcome To Reversi Game ::: ");
        board = new Board("Reversi",8,BLACK_NAME,WHITE_NAME);
        Scanner in = new Scanner(System.in);
        
        try {
            while (!gameEnd){
                System.out.print(getTurnName() + " >> ");
    
                String s = in.nextLine();
                String[] inRowCol = s.split(",");
                int row = Integer.parseInt(inRowCol[0]);
                int col = Integer.parseInt(inRowCol[1]);
                placePiece(row,col);
                if(row == 99) gameEnd = true;
                board.show();
            }
        } catch (Exception e) {
            System.out.println("Invalid arguments.");
        } finally {
            in.close();
        }
 
    }

    static String getTurnName(){
       return isWhiteTurn ? WHITE_NAME : BLACK_NAME;
    }

    static void placePiece(int row, int col){
        boolean hasPlace = false;
        String turn =  getTurnName();
        hasPlace = board.place(turn, row, col);
        if (hasPlace) isWhiteTurn = !isWhiteTurn;
    }
}