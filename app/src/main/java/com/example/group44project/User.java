package com.example.group44project;

import java.util.LinkedList;
import java.util.TreeSet;
import Entities.*;
import Search.*;
public class User implements Comparable<User> {
	private final int uid;
	private Pantry pantry;
	private String name;
	private TreeSet<Tag> bannedTags;

	public User(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pantry getPantry() {
		return pantry;
	}

	public int getUid() {
		return uid;
	}

	public TreeSet<Tag> getBannedTags() {
		return bannedTags;
	}

	public void addBannedTag(Tag t) {
		this.bannedTags.add(t);
	}

	public void removeBannedTag(Tag t) {
		this.bannedTags.remove(t);
	}

	@Override
	public int compareTo(User u){
		return this.uid - u.uid;
	}
}
