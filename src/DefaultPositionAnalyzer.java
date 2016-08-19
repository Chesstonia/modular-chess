import java.util.Collections;
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
		
		VirtualMove move = findHangingMove(virtualBoard);
		if (move == null){
			List<VirtualMove> moves = getMoves(virtualBoard);
			Collections.shuffle(moves);
			LucidMoveMaker moveMaker = new LucidMoveMaker();
			String fen = virtualBoard.getFEN();
			for (VirtualMove virtualMove : moves){
				moveMaker.makeMove(virtualMove.toString(), virtualBoard);
				if (findHangingMove(virtualBoard) == null){
					move = virtualMove;
				}
				virtualBoard.setFEN(fen);
				if (move != null)
					break;
			}
			if (move == null) move = moves.get(0);
		}
		analysis.setBestMove(move);
		return analysis;		
	}

	private VirtualMove findHangingMove(VirtualBoard virtualBoard) {
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
