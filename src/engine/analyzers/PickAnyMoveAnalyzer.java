package engine.analyzers;

import engine.*;

public class PickAnyMoveAnalyzer implements PositionAnalyzer {
	private MoveGenerator generator;

	public PickAnyMoveAnalyzer(MoveGenerator generator){
		this.generator = generator;
	}
	
	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		VirtualBoard board = analysis.getBoard();
		analysis.setBestMove(generator.generateMoves(board).get(0), "just because");
		return analysis;
	}

}
