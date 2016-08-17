import net.humbleprogrammer.maxx.Board;
import net.humbleprogrammer.maxx.Move;
import net.humbleprogrammer.maxx.factories.BoardFactory;
import net.humbleprogrammer.maxx.factories.MoveFactory;

public class LucidMoveValidator implements MoveValidator {

	@Override
	public boolean isValidMove(String moveStr, VirtualBoard virtualBoard) {
		LucidMoveGenerator moveGenerator = new LucidMoveGenerator();
		moveGenerator.generateMoves(virtualBoard);
		Board board = BoardFactory.createFromFEN(virtualBoard.getFEN());
		Move result = MoveFactory.fromSAN(board, moveStr);
		return result != null;
	}

}
