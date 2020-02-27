import java.util.Scanner;

class Reversi {
    static Board board;
    static boolean gameEnd;
    static boolean isWhiteTurn;
    static int blackScore;
    static int whiteScore;
    public static void main(String args[]) {
        System.out.println(" ::: Welcome To Reversi Game ::: ");
        board = new Board("Reversi", Constant.BOARD_SIZE, Constant.WHITE_NAME,Constant.BLACK_NAME);
        Scanner in = new Scanner(System.in);

        try {
            while (!gameEnd) {
                System.out.print(getTurnName() + " >> ");

                String s = in.nextLine();
                String[] inRowCol = s.split(",");
                int row = Integer.parseInt(inRowCol[0]);
                int col = Integer.parseInt(inRowCol[1]);
                placePiece(row, col);
                if (row == 99)
                    gameEnd = true;
                board.setMarker(generateMarkerForTurn(getTurnName())); 
                board.show();
                countScore();
            }
        } catch (Exception e) {
            System.out.println("Invalid arguments.");
        } finally {
            in.close();
        }

    }

    static String getTurnName() {
        return isWhiteTurn ? Constant.WHITE_NAME : Constant.BLACK_NAME;
    }

    static void placePiece(int row, int col) {
        boolean hasPlace = false;
        conquer(row, col);
        String turn = getTurnName();
        hasPlace = board.place(turn, row, col);
        if (hasPlace)
            isWhiteTurn = !isWhiteTurn;
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

    static void conquer(int r, int c) {
        int row = r-1;
        int col = c-1;
        Piece[][] boards = board.getBoard();
        int existingPiece = 0;
        try {

            if (((row - 1) >= 0 && (col - 1) >= 0) && ((row + 1) <= 7 && (col + 1) <= 7)) {
                /*
                 * x x x 
                 * - o - 
                 * - - -
                 */
                if (boards[row - 1][col - 1] != null) checkNextPiece(row - 1,col - 1);
                if (boards[row - 1][col] != null) checkNextPiece(row - 1,col);
                if (boards[row - 1][col + 1] != null) checkNextPiece(row - 1,col + 1);
                /*
                 * - - - 
                 * x o x 
                 * - - -
                 */
                if (boards[row][col - 1] != null) checkNextPiece(row,col - 1);
                if (boards[row][col + 1] != null) checkNextPiece(row,col + 1);
                /*
                 * - - - 
                 * - o - 
                 * x x x
                 */
                if (boards[row + 1][col - 1] != null) checkNextPiece(row + 1,col - 1);
                if (boards[row + 1][col] != null) checkNextPiece(row + 1,col);
                if (boards[row + 1][col + 1] != null) checkNextPiece(row + 1,col + 1);
            }
            System.out.println("Existing around pieces :: " + existingPiece);

        } catch (Exception e) {
            System.out.println("Error :: " + e);
        }
    }
    
    static void checkNextPiece(int r, int c){
        int row = r-1;
        int col = c-1;
        Piece[][] boards = board.getBoard();
        System.out.println(" block name :: "+boards[row][col]);
    }

    static String[][] generateMarkerForTurn(String turn){
        String[][] marker = new String[Constant.BOARD_SIZE][Constant.BOARD_SIZE];
        Piece[][] c_board = board.getBoard();
        for(int i = 0;i < Constant.BOARD_SIZE; i++){
            for(int k = 0; k < Constant.BOARD_SIZE;k++){
                if (c_board[i][k] == null){
                    marker[i][k] = "o";
                }
            }
        }
        return marker;
    }
}