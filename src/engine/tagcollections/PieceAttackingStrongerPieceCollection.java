package engine.tagcollections;

import java.util.List;

import engine.Tag;
import engine.TagCollection;

public class PieceAttackingStrongerPieceCollection implements TagCollection {

	private TagCollection underlyingCollection;

	public PieceAttackingStrongerPieceCollection(TagCollection underlyingCollection){
		this.underlyingCollection = underlyingCollection;
	}
	
	@Override
	public void addTag(Tag tag) {
		underlyingCollection.addTag(tag);
		if (!tag.getName().equals("Attacks"))
			return;
		List<String> parameters = tag.getParameters();
		String attackedPieceType = parameters.get(0);
		String attackingPieceType = parameters.get(2);
		if (isBetterPiece(attackedPieceType, attackingPieceType)){
			Tag newTag = new Tag("PieceAttackingStrongerPiece", parameters.get(3), parameters.get(1));
			underlyingCollection.addTag(newTag);
		}
	}

	private boolean isBetterPiece(String betterPiece, String worsePiece) {
		switch(betterPiece){
		case "King": return true;
		case "Queen": 
			if (worsePiece.equals("King")) return false;
			if (worsePiece.equals("Queen")) return false;
			return true;
		case "Rook":
			if (worsePiece.equals("King")) return false;
			if (worsePiece.equals("Queen")) return false;
			if (worsePiece.equals("Rook")) return false;
			return true;
		case "Bishop":
			if (worsePiece.equals("Pawn")) return true;
			return false;
		case "Knight":
			if (worsePiece.equals("Pawn")) return true;
			return false;
		case "Pawn": return false;
		default: throw new RuntimeException();
		}
	}

	@Override
	public Tag getTag(String tagName) {
		return underlyingCollection.getTag(tagName);
	}

	@Override
	public List<Tag> getAll() {
		return underlyingCollection.getAll();
	}

	@Override
	public boolean hasTag(String tagName) {
		return underlyingCollection.hasTag(tagName);
	}

}
