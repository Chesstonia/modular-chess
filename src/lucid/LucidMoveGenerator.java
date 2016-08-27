package lucid;

import java.util.ArrayList;
import java.util.List;

import engine.MoveGenerator;
import engine.VirtualBoard;
import net.humbleprogrammer.maxx.Board;
import net.humbleprogrammer.maxx.Move;
import net.humbleprogrammer.maxx.MoveList;
import net.humbleprogrammer.maxx.factories.BoardFactory;
import net.humbleprogrammer.maxx.factories.MoveFactory;

public class LucidMoveGenerator implements MoveGenerator {
	@Override
	public List<String> generateMoves(VirtualBoard virtualBoard) {
		String fen = virtualBoard.getFEN();
		Board board = BoardFactory.createFromFEN(fen);
		MoveList list = MoveList.generate(board);
		ArrayList<String> result = translateLucidMovesToVirtual(board, list);
		return result;
	}

	private ArrayList<String> translateLucidMovesToVirtual(Board board, MoveList list) {
		ArrayList<String> result = new ArrayList<String>();
		LucidSquareTranslator translator = new LucidSquareTranslator();
		for (Move move : list){
			result.add(MoveFactory.toSAN(board,  move,  false));
		}
		return result;
	}
	

}
