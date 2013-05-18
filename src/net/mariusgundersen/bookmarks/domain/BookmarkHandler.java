package net.mariusgundersen.bookmarks.domain;

import qvc.handlers.Handler;

public class BookmarkHandler implements Handler {

	private BookmarkCollection bookmarks;
	
	
	
	public BookmarkHandler(BookmarkCollection bookmarks) {
		this.bookmarks = bookmarks;
	}

	public void setSessionId(String sessionId) {
		
	}


	
	
	
	public void handle(AddBookmark bookmark){
		bookmarks.addBookmark(new Bookmark(bookmark.url, bookmark.name));
	}
}
