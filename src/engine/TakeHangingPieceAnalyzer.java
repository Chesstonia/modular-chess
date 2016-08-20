package engine;

import java.util.List;

import net.humbleprogrammer.humble.BitUtil;
import net.humbleprogrammer.maxx.Board;
import net.humbleprogrammer.maxx.Evaluator;
import net.humbleprogrammer.maxx.Square;
import net.humbleprogrammer.maxx.factories.BoardFactory;

public class TakeHangingPieceAnalyzer implements PositionAnalyzer {

	private MoveGenerator moveGenerator;

	public TakeHangingPieceAnalyzer(MoveGenerator moveGenerator){
		this.moveGenerator = moveGenerator;
	}
	
	@Override
	public Analysis performAnalysis(VirtualBoard board) {
		Analysis result = null;
		VirtualMove move = findMoveToTakeHangingPiece(board);
		if (move != null)
			result = new Analysis(move, "found a hanging piece");
		return result;
	}

	private VirtualMove findMoveToTakeHangingPiece(VirtualBoard virtualBoard) {
		List<VirtualMove> moves = getMoves(virtualBoard);
		Board board = BoardFactory.createFromFEN(virtualBoard.getFEN());
		long bbCaptures = Evaluator.findEnPrisePieces(board);
		for ( long bb = bbCaptures; bb != 0L; bb &= (bb - 1) )
        {
			String square = Square.toString(BitUtil.first(bb));
			for (VirtualMove move : moves){
				if (move.toString().contains(square)){
					return move;
				}
			}
        }
		return null;
	}

	private List<VirtualMove> getMoves(VirtualBoard virtualBoard) {
		return moveGenerator.generateMoves(virtualBoard);
	}
}
