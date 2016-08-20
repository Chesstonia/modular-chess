package engine;

public class VirtualMove {
	private String string;
	
	public VirtualMove(String iSqFrom, String iSqTo, String string) {
		this.string = string;
	}

	@Override
	public String toString(){
		return string;
	}
}
