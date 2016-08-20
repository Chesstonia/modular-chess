package engine;

public class CompositePositionAnalyzer implements PositionAnalyzer {
	private PositionAnalyzer[] analyzers;

	public CompositePositionAnalyzer(PositionAnalyzer ...analyzers){
		this.analyzers = analyzers;
	}

	@Override
	public Analysis performAnalysis(VirtualBoard board) {
		for (PositionAnalyzer analyzer : analyzers){
			Analysis analysis = analyzer.performAnalysis(board);
			if (analysis != null)
				return analysis;
		}
		return null;
	}
}
