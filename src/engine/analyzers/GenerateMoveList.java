package engine.analyzers;

import java.util.List;

import engine.*;

public class GenerateMoveList implements PositionAnalyzer {
	private MoveGenerator generator;

	public GenerateMoveList(MoveGenerator generator){
		this.generator = generator;
	}
	
	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		VirtualBoard board = analysis.getBoard();
		List<String> moves = generator.generateMoves(board);
		Tag tag = new Tag("MoveList", moves);
		return analysis.addTag(tag);
	}

}
