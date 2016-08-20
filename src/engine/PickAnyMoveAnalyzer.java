package engine;

public class PickAnyMoveAnalyzer implements PositionAnalyzer {
	private MoveGenerator generator;

	public PickAnyMoveAnalyzer(MoveGenerator generator){
		this.generator = generator;
	}
	
	@Override
	public Analysis performAnalysis(VirtualBoard board) {
		return new Analysis(generator.generateMoves(board).get(0), "just because");
	}

}
