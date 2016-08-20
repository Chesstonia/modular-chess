package engine;

import java.util.Collections;
import java.util.List;

import lucid.LucidMoveMaker;
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
		VirtualMove move = findMoveToTakeHangingPiece(virtualBoard);
		if (move != null)
			return createAnalysis(move, "found a hanging piece");
		
		List<VirtualMove> moves = getMoves(virtualBoard);
		Collections.shuffle(moves);
		move = findMoveThatDoesntHangPiece(virtualBoard, move, moves);
		if (move != null) 
			return createAnalysis(move, "found a move that doesn't hang a piece");
		
		return createAnalysis(moves.get(0), "just picking a random move");
	}

	private Analysis createAnalysis(VirtualMove move, String reason) {
		Analysis analysis = new Analysis();		
		analysis.setBestMove(move);
		return analysis;
	}

	private VirtualMove findMoveThatDoesntHangPiece(VirtualBoard virtualBoard, VirtualMove move,
			List<VirtualMove> moves) {
		LucidMoveMaker moveMaker = new LucidMoveMaker();
		String fen = virtualBoard.getFEN();
		for (VirtualMove virtualMove : moves){
			moveMaker.makeMove(virtualMove.toString(), virtualBoard);
			if (findMoveToTakeHangingPiece(virtualBoard) == null){
				move = virtualMove;
			}
			virtualBoard.setFEN(fen);
			if (move != null)
				break;
		}
		return move;
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
