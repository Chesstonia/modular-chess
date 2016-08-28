package engine.analyzers;

import java.util.List;

import engine.Analysis;
import engine.MoveGenerator;
import engine.PositionAnalyzer;
import engine.Tag;
import engine.VirtualBoard;

public class GenerateMoveListAnalyzer implements PositionAnalyzer {
	private MoveGenerator generator;

	public GenerateMoveListAnalyzer(MoveGenerator generator){
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
