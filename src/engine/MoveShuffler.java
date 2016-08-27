package engine;

import java.util.Collections;
import java.util.List;

public class MoveShuffler implements MoveGenerator {

	private MoveGenerator moveGenerator;

	public MoveShuffler(MoveGenerator moveGenerator){
		this.moveGenerator = moveGenerator;
	}
	
	@Override
	public List<String> generateMoves(VirtualBoard board) {
		List<String> result = this.moveGenerator.generateMoves(board);
		Collections.shuffle(result);
		return result;
	}

}
