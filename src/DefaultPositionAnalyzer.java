import java.util.List;

import net.humbleprogrammer.humble.BitUtil;
import net.humbleprogrammer.maxx.Board;
import net.humbleprogrammer.maxx.Evaluator;
import net.humbleprogrammer.maxx.Square;
import net.humbleprogrammer.maxx.factories.BoardFactory;

public class DefaultPositionAnalyzer implements PositionAnalyzer {

	private MoveGenerator moveGenerator;

	public DefaultPositionAnalyzer(MoveGenerator generator){
		this.moveGenerator = generator;
	}
	
	@Override
	public Analysis performAnalysis(VirtualBoard virtualBoard) {
		Analysis analysis = new Analysis();
		List<VirtualMove> moves = moveGenerator.generateMoves(virtualBoard);
		
		Board board = BoardFactory.createFromFEN(virtualBoard.getFEN());
		long bbCaptures = Evaluator.findEnPrisePieces(board);

		for ( long bb = bbCaptures; bb != 0L; bb &= (bb - 1) )
        {
			String square = Square.toString(BitUtil.first(bb));
			for (VirtualMove move : moves){
				if (move.toString().contains(square)){
					analysis.setBestMove(move);
					break;
				}
					
			}
        }
		
		analysis.setBestMove(moves.get(0));
		return analysis;
	}

}
