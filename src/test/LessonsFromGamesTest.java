package test;

import org.junit.Test;
import static org.junit.Assert.*;

import engine.Analysis;
import engine.VirtualBoard;
import engine.analyzers.TopLevelAlgorithm;

public class LessonsFromGamesTest {
	@Test
	public void takesProtectedPiecesWhenPossible(){
	    testBestMoveOneOfAFew("rnb1kbnr/1pp1pppp/p7/5q2/3P4/2NB4/PPP2PPP/R1BQK1NR b KQkq - 3 5", "Qa5", "Qg4", "Qe6","Qd7");
	}
	
	private void testBestMoveOneOfAFew(String fen, String...optionsForMoves) {
		VirtualBoard board = new VirtualBoard(fen);
		Analysis analysis = new TopLevelAlgorithm(false).improveAnalysis(new Analysis(board));
		boolean foundMove = false;
		for (String move : optionsForMoves)
			if (analysis.getBestMove().equals(move)){
				foundMove = true;
				break;
			}
		assertTrue(analysis.toString(), foundMove);
	}
}
