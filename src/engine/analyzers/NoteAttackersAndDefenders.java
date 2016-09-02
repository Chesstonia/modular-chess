package engine.analyzers;

import java.util.ArrayList;
import java.util.List;

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
			
			tagAll(analysis, board, i, color, "Defenders");
			tagAll(analysis, board, i, color ^ 1, "Attackers");
		}
		return analysis;
	}

	private void tagAll(Analysis analysis, Board board, int square, int color, String tagName) {
		List<String> parameters = new ArrayList<String>();
		for ( long bb = Evaluator.getAttackedBy(board, square, color); bb != 0L; bb &= (bb - 1) )
			parameters.add(translator.get(BitUtil.first(bb)));
		if (!parameters.isEmpty()){
			parameters.add(0, translator.get(square));
			analysis.addTag(new Tag(tagName, parameters));
		}
	}

}
