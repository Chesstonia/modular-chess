package test;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.*;
import lucid.LucidMoveGenerator;

public class BasicTest {

	@Test
	public void capturesHangingPieces() {
		test("7k/p7/1P6/8/8/8/8/7K b - -", "axb6");
	}

	@Test
	public void doesntHangPieces(){
		testNot("8/p7/8/1P6/8/8/8/5k1K b - -", "a6");
	}
	
	private void test(String fen, String bestMove) {
		PositionAnalyzer analyzer = new DefaultPositionAnalyzer(new LucidMoveGenerator());
		VirtualBoard board = new VirtualBoard(fen);
		Analysis analysis = analyzer.performAnalysis(board);
		assertEquals(bestMove, analysis.getBestMove());
	}	
	private void testNot(String fen, String badMove) {
		PositionAnalyzer analyzer = new DefaultPositionAnalyzer(new LucidMoveGenerator());
		VirtualBoard board = new VirtualBoard(fen);
		Analysis analysis = analyzer.performAnalysis(board);
		assertNotEquals(badMove, analysis.getBestMove());
	}

}
