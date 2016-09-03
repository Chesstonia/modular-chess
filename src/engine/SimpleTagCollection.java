package engine;

import java.util.ArrayList;
import java.util.List;

public class SimpleTagCollection implements TagCollection {
	private ArrayList<Tag> tags;

	public SimpleTagCollection(){
		this.tags = new ArrayList<Tag>();
	}
	@Override
	public void addTag(Tag tag) {
		tags.add(tag);
	}

	@Override
	public Tag getTag(String tagName) {
		Tag tag = findTag(tagName);
		if (tag != null) return tag;
		throw new RuntimeException("Missing tag " + tagName);
	}
	private Tag findTag(String tagName) {
		Tag result = null;
		for (Tag tag : tags)
			if (tag.getName().equals(tagName))
				result = tag;
		return result;
	}

	@Override
	public List<Tag> getAll() {
		return tags;
	}

	@Override
	public boolean hasTag(String tagName) {
		return findTag(tagName) != null;
	}

}
