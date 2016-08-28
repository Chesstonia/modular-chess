package engine.analyzers;

import engine.Analysis;
import engine.MoveGenerator;
import engine.MoveShuffler;
import engine.PositionAnalyzer;
import lucid.LucidMoveGenerator;

public class BestAnalyzer implements PositionAnalyzer {

	private PositionAnalyzer analyzer;

	public BestAnalyzer(boolean withShuffledMoves){
		MoveGenerator moveGenerator = new LucidMoveGenerator();
		if (withShuffledMoves)
			moveGenerator = new MoveShuffler(moveGenerator);
		PositionAnalyzer analyzer = new CompositePositionAnalyzer(
				new GenerateMoveListAnalyzer(moveGenerator),
				new FindHangingPiecesAnalyzer(),
				new FindMovesToTakeHangingPiecesAnalyzer(),
				new TakeHangingPieceAnalyzer(),
				new FindMoveThatDoesntHangPieceAnalyzer(moveGenerator),
				new PickAnyMoveAnalyzer());
		this.analyzer = analyzer;
	}
	
	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		return this.analyzer.improveAnalysis(analysis);
	}

}
