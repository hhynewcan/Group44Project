package com.example.group44project;

import java.util.TreeSet;

import Entities.Tag;

public class Ingredient {

	private final String name;

	private TreeSet<Tag> tags;  // TreeSets are auto sorted, so convenient for this purpose. Tags will be relatively
								// few, so no reason to incur the additional compute overhead of an AVL tree

	public Ingredient(String name) {
		this.name = name;
	}

	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

	public void removeTag(Tag tag) {
		this.tags.remove(tag);
	}

	public String getName() {
		return this.name;
	}

	public TreeSet<Tag> getTags() {
		return this.tags;
	}

	@Override
	public String toString() {
		return name;
	}
}
