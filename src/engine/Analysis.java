package engine;

public class Analysis {
	private String bestMove = "";
	
	public String getBestMove() {
		return bestMove;
	}

	public String getReasoning() {
		return "because";
	}

	public void setBestMove(VirtualMove move) {
		bestMove = move.toString();
	}

}
