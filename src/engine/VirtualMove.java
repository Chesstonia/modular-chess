package engine;

public class VirtualMove {
	private String from, to;
	private String string;
	
	public VirtualMove(String iSqFrom, String iSqTo, String string) {
		this.from = iSqFrom;
		this.to = iSqTo;
		this.string = string;
	}

	@Override
	public String toString(){
		return string;
	}
}
