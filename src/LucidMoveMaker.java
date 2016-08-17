import net.humbleprogrammer.maxx.Board;
import net.humbleprogrammer.maxx.Move;
import net.humbleprogrammer.maxx.factories.BoardFactory;
import net.humbleprogrammer.maxx.factories.MoveFactory;

public class LucidMoveMaker implements MoveMaker {

	@Override
	public void makeMove(String moveStr, VirtualBoard virtualBoard) {
		Board board = BoardFactory.createFromFEN(virtualBoard.getFEN());
		Move move = MoveFactory.fromSAN(board, moveStr);
		board.makeMove(move);
		String resultingFEN = BoardFactory.exportFEN(board);
		virtualBoard.setFEN(resultingFEN);
	}

}
