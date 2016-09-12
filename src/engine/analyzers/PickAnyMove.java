package engine.analyzers;

import engine.*;

public class PickAnyMove implements PositionAnalyzer {
	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		analysis.setBestMove(analysis.getTag("MoveList").getParameters().get(0), "just because");
		analysis.log("couldn't come up with anything better, so picking a random move");
		return analysis;
	}

}
