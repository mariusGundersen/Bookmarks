package net.mariusgundersen.bookmarks.domain;

import javax.validation.constraints.Pattern;
import org.apache.bval.constraints.NotEmpty;
import qvc.executables.Command;

public class AddBookmark extends Command {
	

	@NotEmpty(message="You must enter a url")
	@Pattern(regexp="^http(s?)://.*", flags = {Pattern.Flag.CASE_INSENSITIVE}, message="The url must start with http(s)://")
	public final String url;
	
	
	@NotEmpty(message="You must enter a name for the bookmark")
	public final String name;
	
	public AddBookmark(String url, String name) {
		this.url = url;
		this.name = name;
	}
}
