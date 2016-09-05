package engine.analyzers;

import engine.Analysis;
import engine.PositionAnalyzer;
import engine.Tag;
import lucid.LucidSquareTranslator;
import net.humbleprogrammer.humble.BitUtil;
import net.humbleprogrammer.maxx.Board;
import net.humbleprogrammer.maxx.Constants;
import net.humbleprogrammer.maxx.Evaluator;
import net.humbleprogrammer.maxx.Piece;
import net.humbleprogrammer.maxx.factories.BoardFactory;

public class NoteAttackersAndDefenders implements PositionAnalyzer {

	private LucidSquareTranslator translator;

	public NoteAttackersAndDefenders(){
		this.translator = new LucidSquareTranslator();
	}
	
	public Analysis improveAnalysis(Analysis analysis) {
		String fen = analysis.getBoard().getFEN();
		Board board = BoardFactory.createFromFEN(fen);
		
		for (int i = 0; i < 64; i++){
			int piece = board.get(i);
			int color = Piece.getColor(piece);
			if (color == Constants.INVALID)
				continue;
			
			tagAll(analysis, board, i, color, "Defends");
			tagAll(analysis, board, i, color ^ 1, "Attacks");
		}
		analysis.addTag(new Tag("DoneAttackersAndDefenders"));
		return analysis;
	}

	private void tagAll(Analysis analysis, Board board, int square, int color, String tagName) {
		String targetSquare = translator.get(square);
		String targetType = translator.getPieceType(board, square);
		for ( long bb = Evaluator.getAttackedBy(board, square, color); bb != 0L; bb &= (bb - 1) ){
			int lucidAttackerSquare = BitUtil.first(bb);
			String attackerSquare = translator.get(lucidAttackerSquare);
			String attackerType = translator.getPieceType(board, lucidAttackerSquare);
			Tag tag = new Tag(tagName, targetType, targetSquare, attackerType, attackerSquare);
			analysis.addTag(tag);
		}
	}
}
