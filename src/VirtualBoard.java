
public class VirtualBoard {

	private String fen;

	public VirtualBoard(String FEN){
		this.fen = FEN;
	}
	
	public String getFEN() {
		return fen;
	}

	public void setFEN(String fen) {
		this.fen = fen;
	}

}
