package engine;

import java.util.List;

public interface TagCollection {

	void addTag(Tag tag);

	Tag getTag(String tagName);

	List<Tag> getAll();

	boolean hasTag(String tagName);

}
