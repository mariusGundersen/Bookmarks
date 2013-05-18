package net.mariusgundersen.bookmarks.domain;

import qvc.executables.Command;

public class AddBookmark extends Command {

	public final String title;
	public final String url;
	
	
	
	
	public AddBookmark(String title, String url) {
		this.title = title;
		this.url = url;
	}
}
