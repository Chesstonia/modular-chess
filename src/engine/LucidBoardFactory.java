package engine;

public class LucidBoardFactory implements IBoardFactory {

	public VirtualBoard createFromFEN(String fen) {
		return new VirtualBoard(fen);
	}

}
