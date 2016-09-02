package engine.analyzers;

import engine.*;
import lucid.LucidMoveGenerator;

public class TopLevelAlgorithm implements PositionAnalyzer {

	private PositionAnalyzer analyzer;

	public TopLevelAlgorithm(boolean withShuffledMoves){
		MoveGenerator moveGenerator = new LucidMoveGenerator();
		if (withShuffledMoves)
			moveGenerator = new MoveShuffler(moveGenerator);
		PositionAnalyzer analyzer = new CompositePositionAnalyzer(
				new GenerateMoveList(moveGenerator),
				new NoteAttackersAndDefenders(),
				new FindHangingPieces(),
				new FindHangingPieceCaptures(),
				new TakeHangingPiece(),
				new FindMoveThatDoesntHangPiece(moveGenerator),
				new PickAnyMove());
		this.analyzer = analyzer;
	}
	
	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		return this.analyzer.improveAnalysis(analysis);
	}

}
