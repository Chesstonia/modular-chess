package engine.analyzers;

import java.util.List;

import engine.*;

public class FindHangingPieceCaptures implements PositionAnalyzer {

	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		List<String> moves = analysis.getTag("MoveList").getParameters();
		if (!analysis.hasTag("HangingPieceOn"))
			return analysis;
		List<String> hangingPieceSquares = analysis.getTag("HangingPieceOn").getParameters();
		for (String hangingPieceSquare : hangingPieceSquares){
			analysis.log("Trying to find a way to capture the hanging piece on " + hangingPieceSquare);
			for (String move : moves){
				if (move.contains(hangingPieceSquare)){
					analysis.log(move + " does it!");
					analysis.addTag(new Tag("HangingPieceCapture", move));
				}
			}
		}
		return analysis;
	}

}
