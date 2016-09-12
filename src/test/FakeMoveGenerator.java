package test;

import java.util.ArrayList;
import java.util.List;

import engine.MoveGenerator;
import engine.VirtualBoard;

public class FakeMoveGenerator implements MoveGenerator {

	private String[] moves;

	public FakeMoveGenerator(String...moves){
		this.moves = moves;
	}

	@Override
	public List<String> generateMoves(VirtualBoard board) {
		ArrayList<String> moves = new ArrayList<String>();
		for (String move : this.moves)
			moves.add(move);
		return moves;
	}

}
