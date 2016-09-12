package engine.analyzers;

import engine.*;
import lucid.LucidMoveGenerator;

public class TopLevelAlgorithm implements PositionAnalyzer {

	private PositionAnalyzer analyzer;
	private MoveGenerator moveGenerator;

	public TopLevelAlgorithm(MoveGenerator moveGenerator){
		this.moveGenerator = moveGenerator;
		
		initialize();
	}
	
	public TopLevelAlgorithm(boolean withShuffledMoves){
		this.moveGenerator = new LucidMoveGenerator();
		if (withShuffledMoves)
			moveGenerator = new MoveShuffler(moveGenerator);
		initialize();
	}
	
	private void initialize() {
		PositionAnalyzer analyzer = new CompositePositionAnalyzer(
				new FindTacticalMotifs(moveGenerator),
				new TakeHangingPiece(),
				new TakeStrongerPiece(),
				new FindMoveThatDoesntHangPiece(),
				new PickAnyMove());
		this.analyzer = analyzer;
	}
	
	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		return this.analyzer.improveAnalysis(analysis);
	}

}
