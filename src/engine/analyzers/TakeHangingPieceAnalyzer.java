package engine.analyzers;

import engine.Analysis;
import engine.PositionAnalyzer;

public class TakeHangingPieceAnalyzer implements PositionAnalyzer {

	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		if (!analysis.hasTag("HangingPieceCapture"))
			return analysis;
		String move = analysis.getTag("HangingPieceCapture").getParameters().get(0);
		analysis.setBestMove(move, "take hanging piece");
		analysis.done();
		return analysis;
	}

}
