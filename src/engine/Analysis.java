package engine;

public class Analysis {
	private String bestMove = "";
	private String reason = "because";
	
	public Analysis(VirtualMove move, String reason) {
		this.bestMove = move.toString();
		this.reason = reason;
	}

	public String getBestMove() {
		return bestMove;
	}

	public String getReasoning() {
		return reason;
	}

	public void setBestMove(VirtualMove move) {
		bestMove = move.toString();
	}

}
