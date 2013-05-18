package net.mariusgundersen.bookmarks.domain;

import qvc.executables.Command;

public class AddBookmark extends Command {

	public final String name;
	public final String url;
	
	
	
	
	public AddBookmark(String name, String url) {
		this.name = name;
		this.url = url;
	}
}
