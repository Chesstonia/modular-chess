package engine;

import java.util.List;

import lucid.LucidMoveMaker;
import net.humbleprogrammer.humble.BitUtil;
import net.humbleprogrammer.maxx.Board;
import net.humbleprogrammer.maxx.Evaluator;
import net.humbleprogrammer.maxx.Square;
import net.humbleprogrammer.maxx.factories.BoardFactory;

public class FindMoveThatDoesntHangPiece implements PositionAnalyzer {
	private MoveGenerator generator;

	public FindMoveThatDoesntHangPiece(MoveGenerator generator){
		this.generator = generator;
	}
	
	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		VirtualBoard board = analysis.getBoard();
		VirtualMove move = findMoveThatDoesntHangPiece(board, generator.generateMoves(board));
		if (move != null){
			analysis.setBestMove(move, "found a move that doesn't hang a piece");
			analysis.done();
		}
		return analysis;
	}

	private VirtualMove findMoveThatDoesntHangPiece(VirtualBoard virtualBoard, List<VirtualMove> moves) {
		LucidMoveMaker moveMaker = new LucidMoveMaker();
		String fen = virtualBoard.getFEN();
		for (VirtualMove virtualMove : moves){
			moveMaker.makeMove(virtualMove.toString(), virtualBoard);
			VirtualMove refutation = findMoveToTakeHangingPiece(virtualBoard);
			virtualBoard.setFEN(fen);
			if (refutation == null)
				return virtualMove;
		}
		return null;
	}
	

	private VirtualMove findMoveToTakeHangingPiece(VirtualBoard virtualBoard) {
		List<VirtualMove> moves = generator.generateMoves(virtualBoard);
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
}
