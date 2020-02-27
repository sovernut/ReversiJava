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
        board.place(Constant.BLACK_NAME, 4, 4);
        board.place(Constant.BLACK_NAME, 5, 5);
        board.place(Constant.WHITE_NAME, 4, 5);
        board.place(Constant.WHITE_NAME, 5, 4);
        try {
            while (!gameEnd) {
                System.out.print(getTurnName() + " >> ");

                String s = in.nextLine();
                String[] inRowCol = s.split(" ");
                int row = Integer.parseInt(inRowCol[0]);
                int col = Integer.parseInt(inRowCol[1]);
                placePiece(row, col);
                if (row == 99)
                    gameEnd = true;
                String[][] m = generateMarkerForTurn(getTurnName());
                board.setMarker(m); 
                board.show();
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

    static String getEnemyTurnName(String turn){
        return turn == Constant.WHITE_NAME ? Constant.BLACK_NAME : Constant.WHITE_NAME;
    }

    static void placePiece(int row, int col) {
        boolean hasPlace = false;
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

    static String[][] generateMarkerForTurn(String turn){
        String[][] marker = new String[Constant.BOARD_SIZE][Constant.BOARD_SIZE];
        Piece[][] c_board = board.getBoard();
        String enemy = getEnemyTurnName(turn);
        for(int i = 0;i < Constant.BOARD_SIZE; i++){
            for(int k = 0; k < Constant.BOARD_SIZE;k++){        
                if (c_board[i][k] != null && c_board[i][k].getName().equals(enemy)){
                    markPlaceable(marker, turn, i, k);
                }
            }
        }
        return marker;
    }

    static void markPlaceable(String[][] marker, String turn, int x_p, int y_p){
        int x[] = {-1,-1,-1, 0, 0, 1, 1, 1};
        int y[] = {-1, 0, 1,-1, 1,-1, 0, 1};
        for (int i = 0;i < x.length;i++){
            boolean isInBoard = board.isInBoard(x[i] + x_p, y[i] + y_p);
            boolean isAllyPieceOpposite = isAllyPieceOpposite(turn, x_p + x[i] * -1, y_p + y[i] * -1, x[i] * -1, y[i] * -1);
            // System.out.println("========" + (x[i] + x_p + 1) + " " + (y[i] + y_p + 1) + "===============");
            // System.out.println("isINBOARD "+isInBoard);
            // System.out.println("isAllyPieceOpposite "+isAllyPieceOpposite);
            if (isInBoard && isAllyPieceOpposite && !board.isCellPlaced(x[i] + x_p, y[i] + y_p)) {
                marker[x[i] + x_p][y[i] + y_p] = "o";
            }
        }
    }

    static boolean isAllyPieceOpposite(String turn, int x, int y, int x_dir, int y_dir){
        Piece[][] c_board = board.getBoard();
        while (board.isInBoard(x, y) && board.isCellPlaced(x, y)){
            if (c_board[x][y].getName().equals(turn)){
                return true;
            }
            x += x_dir;
            y += y_dir;
        }
        return false;
    }
}