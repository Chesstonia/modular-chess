package engine.analyzers;

import engine.Analysis;
import engine.MoveGenerator;
import engine.PositionAnalyzer;

public class FindTacticalMotifs implements PositionAnalyzer {
	PositionAnalyzer analyzer;
	
	public FindTacticalMotifs(MoveGenerator generator){
		this.analyzer = new CompositePositionAnalyzer(
			new GenerateMoveList(generator),
			new FindMates(),
			new NoteAttackersAndDefenders(),
			new FindHangingPieces(),
			new FindHangingPieceCaptures());
	}

	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		analysis.log("finding tactical motifs");
		return analyzer.improveAnalysis(analysis);
	}
}
