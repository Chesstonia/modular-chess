package engine;

public class CompositePositionAnalyzer implements PositionAnalyzer {
	private PositionAnalyzer[] analyzers;

	public CompositePositionAnalyzer(PositionAnalyzer ...analyzers){
		this.analyzers = analyzers;
	}

	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		for (PositionAnalyzer analyzer : analyzers){
			analyzer.improveAnalysis(analysis);
			if (analysis.isComplete())
				return analysis;
		}
		return null;
	}
}
