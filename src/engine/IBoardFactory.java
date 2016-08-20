package engine;

public interface IBoardFactory {

	VirtualBoard createFromFEN(String fen);

}
