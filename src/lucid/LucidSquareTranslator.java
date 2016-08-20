package lucid;

import java.util.ArrayList;

public class LucidSquareTranslator {

	private ArrayList<String> squares;

	public LucidSquareTranslator(){
		squares = new ArrayList<String>();
		squares.add("a1"); squares.add("b1"); squares.add("c1"); squares.add("d1");
		squares.add("e1"); squares.add("f1"); squares.add("g1"); squares.add("h1");
		
		squares.add("a2"); squares.add("b2"); squares.add("c2"); squares.add("d2");
		squares.add("e2"); squares.add("f2"); squares.add("g2"); squares.add("h2");
		
		squares.add("a3"); squares.add("b3"); squares.add("c3"); squares.add("d3");
		squares.add("e3"); squares.add("f3"); squares.add("g3"); squares.add("h3");
		
		squares.add("a4"); squares.add("b4"); squares.add("c4"); squares.add("d4");
		squares.add("e4"); squares.add("f4"); squares.add("g4"); squares.add("h4");
	
		squares.add("a5"); squares.add("b5"); squares.add("c5"); squares.add("d5");
		squares.add("e5"); squares.add("f5"); squares.add("g5"); squares.add("h5");
	
		squares.add("a6"); squares.add("b6"); squares.add("c6"); squares.add("d6");
		squares.add("e6"); squares.add("f6"); squares.add("g6"); squares.add("h6");
		
		squares.add("a7"); squares.add("b7"); squares.add("c7"); squares.add("d7");
		squares.add("e7"); squares.add("f7"); squares.add("g7"); squares.add("h7");
		
		squares.add("a8"); squares.add("b8"); squares.add("c8"); squares.add("d8");
		squares.add("e8"); squares.add("f8"); squares.add("g8"); squares.add("h8");
	}

	public String get(int iSqFrom) {
		return squares.get(iSqFrom);
	}
}
