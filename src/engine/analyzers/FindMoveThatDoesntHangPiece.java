package engine.analyzers;

import java.util.List;

import engine.Analysis;
import engine.PositionAnalyzer;
import lucid.LucidMoveGenerator;
import lucid.LucidMoveMaker;

public class FindMoveThatDoesntHangPiece implements PositionAnalyzer {
	
	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		String move = findMoveThatDoesntHangPiece(analysis, analysis.getTag("MoveList").getParameters());
		if (move != null){
			analysis.log("found a move that doesn't hang a piece: " + move);
			analysis.setBestMove(move, "found a move that doesn't hang a piece");
			analysis.done();
		}
		return analysis;
	}

	private String findMoveThatDoesntHangPiece(Analysis analysis, List<String> moves) {
		LucidMoveMaker moveMaker = new LucidMoveMaker();
		for (String virtualMove : moves){
			new ExpandTree(virtualMove, moveMaker).improveAnalysis(analysis);
			Analysis analysis2 = analysis.analysisAfter(virtualMove);
			new FindTacticalMotifs(new LucidMoveGenerator()).improveAnalysis(analysis2);
			if (!analysis2.hasTag("HangingPieceOn") && 
				!analysis2.hasTag("PieceAttackingStrongerPiece") && 
				!analysis2.hasTag("Mate")){
				return virtualMove;
			}
		}
		return null;
	}
}
