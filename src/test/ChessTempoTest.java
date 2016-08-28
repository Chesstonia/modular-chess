package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import engine.Analysis;
import engine.VirtualBoard;
import engine.analyzers.TopLevelAlgorithm;

public class ChessTempoTest {
	@Test
	public void Problem33380_228(){
		test("r5k1/pp3p1p/6p1/8/3p2b1/5NP1/PPP1rK2/R1BR4 w - - 0 1", "Kxe2");
	}
	
	@Test
    public void Problem25933_455(){
		test("8/2n3p1/2kN2P1/6P1/4K3/8/2P5/8 b - - 0 1","Kxd6");
	}
	
	@Test
	public void Problem56642_416(){
		//test("2qb2k1/1b3ppp/pp1P4/4p3/Q7/P7/1P2NPPP/3R2K1 w - - 0 1", "Qe8");
	}
	
	private void test(String fen, String bestMove) {
		VirtualBoard board = new VirtualBoard(fen);
		Analysis analysis = new TopLevelAlgorithm(false).improveAnalysis(new Analysis(board));
		assertEquals(bestMove, analysis.getBestMove());
	}	
	
}
