package engine;

public class Analysis {
	private String bestMove = "";
	private String reason = "because";
	private VirtualBoard board;
	private boolean done;
	
	public Analysis(VirtualBoard board) {
		this.bestMove = "";
		this.reason = "";
		this.board = board;
		this.done = false;
	}

	public String getBestMove() {
		return bestMove;
	}

	public String getReasoning() {
		return reason;
	}

	public void setBestMove(VirtualMove move, String reason) {
		this.bestMove = move.toString();
		this.reason = reason;
	}

	public boolean isComplete() {
		return done;
	}

	public void done() {
		done = true;
	}

	public VirtualBoard getBoard() {
		return board;
	}

}
