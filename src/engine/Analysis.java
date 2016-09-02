package engine;

import java.util.ArrayList;
import java.util.List;

public class Analysis {
	private String bestMove = "";
	private String reason = "because";
	private VirtualBoard board;
	private boolean done;
	private List<Tag> tags;
	
	public Analysis(VirtualBoard board) {
		this.bestMove = "";
		this.reason = "";
		this.board = board;
		this.done = false;
		this.tags = new ArrayList<Tag>();
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
		tags.add(tag);
		return this;
	}

	public Tag getTag(String tagName) {
		for (Tag tag : tags)
			if (tag.getName().equals(tagName))
				return tag;
		throw new RuntimeException();
	}

	public boolean hasTag(String tagName) {
		for (Tag tag : tags)
			if (tag.getName().equals(tagName))
				return true;
		return false;
	}

	public List<Tag> getTags() {
		return tags;
	}

}
