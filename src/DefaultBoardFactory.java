
public class DefaultBoardFactory implements IBoardFactory {

	@Override
	public VirtualBoard createFromFEN(String fen) {
		return new VirtualBoard(fen);
	}

}
