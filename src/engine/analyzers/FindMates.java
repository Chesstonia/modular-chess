package engine.analyzers;

import engine.Analysis;
import engine.PositionAnalyzer;
import net.humbleprogrammer.maxx.Arbiter;
import net.humbleprogrammer.maxx.Board;
import net.humbleprogrammer.maxx.Move;
import net.humbleprogrammer.maxx.MoveList;
import net.humbleprogrammer.maxx.factories.BoardFactory;
import net.humbleprogrammer.maxx.factories.MoveFactory;

public class FindMates implements PositionAnalyzer {

	@Override
	public Analysis improveAnalysis(Analysis analysis) {
		String fen = analysis.getBoard().getFEN();
		final Board bd = BoardFactory.createFromFEN(fen);
		final MoveList moves = MoveList.generate( bd );

		for ( Move mv : moves )
                {
                Board bdNew = new Board( bd );

                bdNew.makeMove( mv );
                if (Arbiter.isInCheck( bdNew )){
                        MoveList responses = MoveList.generate(bdNew);
                		if (responses.isEmpty()){
                			String moveNotation = MoveFactory.toSAN(bd,  mv,  false);
                			analysis.log("found a mate! " + moveNotation);
							analysis.setBestMove(moveNotation, "Mate");
                			analysis.done();
                			break;
                		}
                }
        }
		return analysis;
	}

}
