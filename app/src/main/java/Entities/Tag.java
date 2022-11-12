package Entities;

public class Tag implements Comparable<Tag> {

	private final String name;

	public Tag(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int compareTo(Tag other) {
		return this.name.compareTo(other.name);
	}
}
