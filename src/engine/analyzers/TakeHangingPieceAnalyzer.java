package engine.analyzers;

import java.util.List;

import engine.*;
import net.humbleprogrammer.humble.BitUtil;
import net.humbleprogrammer.maxx.*;
import net.humbleprogrammer.maxx.factories.BoardFactory;

public class TakeHangingPieceAnalyzer implements PositionAnalyzer {

	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		VirtualBoard board = analysis.getBoard();
		String move = findMoveToTakeHangingPiece(board, analysis);
		if (move != null){
			analysis.setBestMove(move, "found a hanging piece");
			analysis.done();
		}
		return analysis;
	}

	private String findMoveToTakeHangingPiece(VirtualBoard virtualBoard, Analysis analysis) {
		List<String> moves = getMoves(analysis);
		Board board = BoardFactory.createFromFEN(virtualBoard.getFEN());
		long bbCaptures = Evaluator.findEnPrisePieces(board);
		for ( long bb = bbCaptures; bb != 0L; bb &= (bb - 1) )
        {
			String square = Square.toString(BitUtil.first(bb));
			for (String move : moves){
				if (move.toString().contains(square)){
					return move;
				}
			}
        }
		return null;
	}

	private List<String> getMoves(Analysis analysis) {
		return analysis.getTag("MoveList").getParameters();
	}
}
