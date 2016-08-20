package lucid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import engine.MoveGenerator;
import engine.VirtualBoard;
import engine.VirtualMove;
import net.humbleprogrammer.maxx.Board;
import net.humbleprogrammer.maxx.Move;
import net.humbleprogrammer.maxx.MoveList;
import net.humbleprogrammer.maxx.factories.BoardFactory;
import net.humbleprogrammer.maxx.factories.MoveFactory;

public class LucidMoveGenerator implements MoveGenerator {
	@Override
	public List<VirtualMove> generateMoves(VirtualBoard virtualBoard) {
		String fen = virtualBoard.getFEN();
		Board board = BoardFactory.createFromFEN(fen);
		MoveList list = MoveList.generate(board);
		ArrayList<VirtualMove> result = translateLucidMovesToVirtual(board, list);
		return result;
	}

	private ArrayList<VirtualMove> translateLucidMovesToVirtual(Board board, MoveList list) {
		ArrayList<VirtualMove> result = new ArrayList<VirtualMove>();
		LucidSquareTranslator translator = new LucidSquareTranslator();
		for (Move move : list){
			result.add(new VirtualMove(translator.get(move.iSqFrom), translator.get(move.iSqTo), MoveFactory.toSAN(board,  move,  false)));
		}
		Collections.shuffle(result);
		return result;
	}
	

}
