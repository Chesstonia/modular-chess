package lucid;

import engine.IBoardFactory;
import engine.VirtualBoard;

public class LucidBoardFactory implements IBoardFactory {

	public VirtualBoard createFromFEN(String fen) {
		return new VirtualBoard(fen);
	}

}
