package engine;

import java.util.List;

public class Analysis {
	private String bestMove = "";
	private String reason = "because";
	private VirtualBoard board;
	private boolean done;
	private TagCollection tagCollection;
	
	public Analysis(VirtualBoard board) {
		this.bestMove = "";
		this.reason = "";
		this.board = board;
		this.done = false;
		this.tagCollection = new SimpleTagCollection();
	}

	public String getBestMove() {
		return bestMove;
	}

	public String getReasoning() {
		return reason;
	}

	public void setBestMove(String move, String reason) {
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

	public Analysis addTag(Tag tag) {
		tagCollection.addTag(tag);
		return this;
	}

	public Tag getTag(String tagName) {
		return tagCollection.getTag(tagName);
	}

	public boolean hasTag(String tagName) {
		return tagCollection.hasTag(tagName);
	}

	public List<Tag> getTags() {
		return tagCollection.getAll();
	}

}
