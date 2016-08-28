package engine.analyzers;

import engine.*;

public class PickAnyMove implements PositionAnalyzer {
	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		analysis.setBestMove(analysis.getTag("MoveList").getParameters().get(0), "just because");
		return analysis;
	}

}
