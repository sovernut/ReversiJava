import java.util.Scanner;

class Reversi {
    static Board board;
    static boolean gameEnd;
    static boolean isWhiteTurn;
    static int blackScore;
    static int whiteScore;

    public static void main(String args[]) {
        System.out.println(" ::: Welcome To Reversi Game ::: ");
        board = new Board("Reversi", Constant.BOARD_SIZE, Constant.WHITE_NAME, Constant.BLACK_NAME);
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
        String turn = getTurnName();
        hasPlace = board.place(turn, row, col);
        conquer(row, col);
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
        int row = r - 1;
        int col = c - 1;
        Piece[][] boards = board.getBoard();
        try {

            if (((row - 1) >= 0 && (col - 1) >= 0) && ((row + 1) <= 7 && (col + 1) <= 7)) {
                /*
                 * x x x - o - - - -
                 */
                if (boards[row - 1][col - 1] != null)
                    checkNextPiece(row, col, Constant.UPPER_LEFT);
                if (boards[row - 1][col] != null)
                    checkNextPiece(row, col, Constant.UPPER_MID);
                if (boards[row - 1][col + 1] != null)
                    checkNextPiece(row, col, Constant.UPPER_RIGHT);
                /*
                 * - - - x o x - - -
                 */
                if (boards[row][col - 1] != null)
                    checkNextPiece(row, col, Constant.MIDDLE_LEFT);
                if (boards[row][col + 1] != null)
                    checkNextPiece(row, col, Constant.MIDDLE_RIGHT);
                /*
                 * - - - - o - x x x
                 */
                if (boards[row + 1][col - 1] != null)
                    checkNextPiece(row, col, Constant.LOWER_LEFT);
                if (boards[row + 1][col] != null)
                    checkNextPiece(row, col, Constant.LOWER_MID);
                if (boards[row + 1][col + 1] != null)
                    checkNextPiece(row, col, Constant.LOWER_RIGHT);
            }
            // System.out.println("Existing around pieces :: " + existingPiece);

        } catch (Exception e) {
            System.out.println("Error :: " + e);
        }
    }

    static void checkNextPiece(int row, int col, String direction) {
        Piece[][] boards = board.getBoard();
        switch (direction) {
            case Constant.UPPER_LEFT:
                System.out.println(Constant.UPPER_LEFT);
                if (isSameColor(boards[row - 1][col - 1])) {

                } else {
                    System.out.println(" UPPER_LEFT CAN EAT !!");
                }
                break;
            case Constant.UPPER_MID:
                System.out.println(Constant.UPPER_MID);
                if (isSameColor(boards[row - 1][col])) {

                } else {
                    System.out.println("UPPER_MID CAN EAT !!");
                    int count = 0;
                    for (int i = row - 2; i >= 0; i--) {
                        count++;
                        if (isSameColor(boards[i][col])) {
                            for (int j = i + 1; count > 0; count--) {
                                boards[j][col].setNameAndSymbol(getTurnName());
                                j++;
                            }
                            break;
                        }
                    }
                    break;
                }
                break;
            case Constant.UPPER_RIGHT:
                System.out.println(Constant.UPPER_RIGHT);
                if (isSameColor(boards[row - 1][col + 1])) {

                } else {
                    System.out.println("UPPER_RIGHT CAN EAT !!");
                }
                break;
            case Constant.MIDDLE_LEFT:
                System.out.println(Constant.MIDDLE_LEFT);
                if (isSameColor(boards[row][col - 1])) {
                    break;
                } else {
                    System.out.println("MIDDLE_LEFT CAN EAT !!");
                    int count = 0;
                    for (int i = col - 2; i >= 0; i--) {
                        count++;
                        if (isSameColor(boards[row][i])) {
                            // System.out.println("count :: "+count);
                            // System.out.println("boards[row][i] :: ["+row+"]["+i+"]");
                            // System.out.println("EAT THEM ALLLLL!");
                            for (int j = i + 1; count > 0; count--) {
                                boards[row][j].setNameAndSymbol(getTurnName());
                                j++;
                            }
                            break;
                        }
                    }
                    break;
                }

            case Constant.MIDDLE_RIGHT:
                System.out.println(Constant.MIDDLE_RIGHT);
                if (isSameColor(boards[row][col + 1])) {
                    break;
                } else {
                    System.out.println("MIDDLE_RIGHT CAN EAT !!");
                    int count = 0;
                    for (int i = col + 2; i <= 7; i++) {
                        count++;
                        if (isSameColor(boards[row][i])) {
                            for (int j = i - 1; count > 0; count--) {
                                boards[row][j].setNameAndSymbol(getTurnName());
                                j--;
                            }
                            break;
                        }
                    }
                    break;
                }
            case Constant.LOWER_LEFT:
                System.out.println(Constant.LOWER_LEFT);
                if (isSameColor(boards[row + 1][col - 1])) {

                } else {
                    System.out.println("LOWER_LEFT CAN EAT !!");
                }
                break;
            case Constant.LOWER_MID:
                System.out.println(Constant.LOWER_MID);
                if (isSameColor(boards[row + 1][col])) {

                } else {
                    System.out.println("LOWER_MID CAN EAT !!");
                    int count = 0;
                    for (int i = row + 2; i >= 0; i--) {
                        count++;
                        if (isSameColor(boards[i][col])) {
                            for (int j = i + 1; count > 0; count--) {
                                boards[j][col].setNameAndSymbol(getTurnName());
                                j++;
                            }
                            break;
                        }
                    }
                    break;
                }
                break;
            case Constant.LOWER_RIGHT:
                System.out.println(Constant.LOWER_RIGHT);
                if (isSameColor(boards[row + 1][col + 1])) {

                } else {
                    System.out.println("LOWER_RIGHT CAN EAT !!");
                }
                break;
            default:
                break;
        }
    }

    static boolean isSameColor(Piece broad) {
        return (broad.getName() == getTurnName());
    }

    static String[][] generateMarkerForTurn(String turn) {
        String[][] marker = new String[Constant.BOARD_SIZE][Constant.BOARD_SIZE];
        Piece[][] c_board = board.getBoard();
        for (int i = 0; i < Constant.BOARD_SIZE; i++) {
            for (int k = 0; k < Constant.BOARD_SIZE; k++) {
                if (c_board[i][k] == null) {
                    marker[i][k] = "-";
                }
            }
        }
        return marker;
    }
}