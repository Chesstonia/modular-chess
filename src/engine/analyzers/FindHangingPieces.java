package engine.analyzers;

import engine.*;
import net.humbleprogrammer.humble.BitUtil;
import net.humbleprogrammer.maxx.*;
import net.humbleprogrammer.maxx.factories.BoardFactory;

public class FindHangingPieces implements PositionAnalyzer {

	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		VirtualBoard virtualBoard = analysis.getBoard();
		Board board = BoardFactory.createFromFEN(virtualBoard.getFEN());
		long bbCaptures = Evaluator.findEnPrisePieces(board);
		for ( long bb = bbCaptures; bb != 0L; bb &= (bb - 1) )
        {
			String square = Square.toString(BitUtil.first(bb));
			analysis.addTag(new Tag("HangingPieceOn", square));
        }
		return analysis;
	}
}
