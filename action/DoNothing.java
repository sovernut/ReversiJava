package action;
import main.Piece;

public class DoNothing implements IAction {
    public void Action(Piece[][] board) {
        System.out.println("Do nothing!");
    }
}
