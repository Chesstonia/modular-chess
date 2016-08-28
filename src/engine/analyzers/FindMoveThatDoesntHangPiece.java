package engine.analyzers;

import java.util.List;

import engine.*;
import lucid.LucidMoveMaker;
import net.humbleprogrammer.humble.BitUtil;
import net.humbleprogrammer.maxx.*;
import net.humbleprogrammer.maxx.factories.BoardFactory;

public class FindMoveThatDoesntHangPiece implements PositionAnalyzer {
	private MoveGenerator generator;

	public FindMoveThatDoesntHangPiece(MoveGenerator generator){
		this.generator = generator;
	}
	
	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		VirtualBoard board = analysis.getBoard();
		String move = findMoveThatDoesntHangPiece(board, analysis.getTag("MoveList").getParameters());
		if (move != null){
			analysis.setBestMove(move, "found a move that doesn't hang a piece");
			analysis.done();
		}
		return analysis;
	}

	private String findMoveThatDoesntHangPiece(VirtualBoard virtualBoard, List<String> moves) {
		LucidMoveMaker moveMaker = new LucidMoveMaker();
		String fen = virtualBoard.getFEN();
		for (String virtualMove : moves){
			moveMaker.makeMove(virtualMove.toString(), virtualBoard);
			String refutation = findMoveToTakeHangingPiece(virtualBoard);
			virtualBoard.setFEN(fen);
			if (refutation == null)
				return virtualMove;
		}
		return null;
	}
	

	private String findMoveToTakeHangingPiece(VirtualBoard virtualBoard) {
		List<String> moves = generator.generateMoves(virtualBoard);
		Board board = BoardFactory.createFromFEN(virtualBoard.getFEN());
		long bbCaptures = Evaluator.findEnPrisePieces(board);
		for ( long bb = bbCaptures; bb != 0L; bb &= (bb - 1) )
        {
			String square = Square.toString(BitUtil.first(bb));
			for (String move : moves){
				if (move.contains(square)){
					return move;
				}
			}
        }
		return null;
	}
}
