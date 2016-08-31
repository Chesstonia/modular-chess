package test;

import org.junit.Test;

import engine.Analysis;
import engine.PositionAnalyzer;
import engine.VirtualBoard;
import engine.analyzers.TopLevelAlgorithm;

public class IntegrationTest {

	@Test
	public void crashTest() {
		PositionAnalyzer analyzer = new TopLevelAlgorithm(true);
		VirtualBoard board = new VirtualBoard("rn1q1rk1/ppp2ppp/8/3pp2n/7P/P4P1P/P1PPP3/R1BQKBN1 w Q - 1 8");
		Analysis analysis = analyzer.improveAnalysis(new Analysis(board));
		analysis.getBestMove();
	}

}
