package engine.analyzers;

import engine.Analysis;
import engine.PositionAnalyzer;
import engine.Tag;

public class TakeStrongerPiece implements PositionAnalyzer {

	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		if (!analysis.hasTag("PieceAttackingStrongerPiece")) return analysis;
		Tag tag = analysis.getTag("PieceAttackingStrongerPiece");
		Tag moves = analysis.getTag("MoveList");
		String target = tag.getParameters().get(1);
		for (String move : moves.getParameters())
			if (move.contains("x" + target)){
				analysis.setBestMove(move, "Take a stronger piece");
				analysis.done();
			}
		return analysis;
	}

}
