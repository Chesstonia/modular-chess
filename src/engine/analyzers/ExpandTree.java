package engine.analyzers;

import engine.Analysis;
import engine.MoveMaker;
import engine.PositionAnalyzer;
import engine.VirtualBoard;

public class ExpandTree implements PositionAnalyzer {
	private String move;
	private MoveMaker moveMaker;

	public ExpandTree(String move, MoveMaker moveMaker){
		this.move = move;
		this.moveMaker = moveMaker;
	}
	
	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		VirtualBoard board = analysis.getBoard();
		String fen = board.getFEN();
		moveMaker.makeMove(move, board);
		analysis.log("checking out move " + move);
		Analysis newNode = new Analysis(new VirtualBoard(board.getFEN()));
		board.setFEN(fen);
		analysis.addMove(move, newNode);
		return analysis;
	}

}
