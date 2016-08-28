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
		for (String hangingPieceSquare : hangingPieceSquares)
			for (String move : moves)
				if (move.contains(hangingPieceSquare))
					analysis.addTag(new Tag("HangingPieceCapture", move));
		return analysis;
	}

}
