package engine;

import java.util.ArrayList;
import java.util.List;

public class Tag {

	private String tagName;
	private List<String> parameters;

	public Tag(String tagName, List<String> parameters) {
		this.tagName = tagName;
		this.parameters = parameters;
	}

	public Tag(String tagName, String parameter) {
		this.tagName = tagName;
		this.parameters = new ArrayList<String>();
		parameters.add(parameter);
	}

	public String getName() {
		return this.tagName;
	}

	public List<String> getParameters() {
		return parameters;
	}

}
