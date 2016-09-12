package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import engine.Analysis;
import engine.VirtualBoard;
import engine.analyzers.TopLevelAlgorithm;

public class BasicTest {

	@Test
	public void capturesHangingPieces() {
		test("7k/p7/1P6/8/8/8/8/7K b - -", "axb6");
	}

	@Test
	public void doesntHangPieces(){
		testNot("8/p7/8/1P6/8/8/8/5k1K b - -", "a6");
	}
	
	@Test
	public void takesProtectedPiecesWhenPossible(){
	    test("2k5/3q4/4r3/5B2/6Q1/7K/8/8 w - -", "Bxe6");
	}
	
	private void test(String fen, String bestMove) {
		VirtualBoard board = new VirtualBoard(fen);
		Analysis analysis = new TopLevelAlgorithm(false).improveAnalysis(new Analysis(board));
		assertEquals(analysis.toString(), bestMove, analysis.getBestMove());
	}	
	
	private void testNot(String fen, String badMove) {
		VirtualBoard board = new VirtualBoard(fen);
		Analysis analysis = new TopLevelAlgorithm(false).improveAnalysis(new Analysis(board));
		assertNotEquals(analysis.toString(), badMove, analysis.getBestMove());
	}

}
