package net.mariusgundersen.bookmarks.domain;

import javax.validation.constraints.Pattern;

import org.apache.bval.constraints.NotEmpty;

import qvc.executables.Command;

public class AddBookmark extends Command {

	@NotEmpty(message="You must entere a title")
	public final String title;
	
	@NotEmpty(message="You must enter a url")
	@Pattern(regexp = "http://.*", message="The url must start with http://")
	public final String url;
	
	
	
	
	public AddBookmark(String title, String url) {
		this.title = title;
		this.url = url;
	}
}
