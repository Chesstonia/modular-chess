package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import engine.tagcollections.PieceAttackingStrongerPieceCollection;

public class Analysis {
	private String bestMove = "";
	private String reason = "because";
	private VirtualBoard board;
	private boolean done;
	private TagCollection tagCollection;
	private HashMap<String, Analysis> moves;
	private ArrayList<String> log = new ArrayList<String>();
	
	public Analysis(VirtualBoard board) {
		this.bestMove = "";
		this.reason = "";
		this.board = board;
		this.done = false;
		this.moves = new HashMap<String, Analysis>();
		this.tagCollection = new PieceAttackingStrongerPieceCollection(new SimpleTagCollection());
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

	public void addMove(String move, Analysis analysis) {
		this.moves.put(move, analysis);
	}
	
	public Analysis analysisAfter(String move){
		if (!moves.containsKey(move))
			throw new RuntimeException();
		Analysis result = moves.get(move);
		return result;
	}

	public void log(String string) {
		log.add(string);
	}

	@Override
	public String toString(){
		String result = "";
		for (String string : log)
			result += string + "\n";
		for (String string : moves.keySet())
			result += string + ": " + analysisAfter(string).toString();
		return result;
	}
}
