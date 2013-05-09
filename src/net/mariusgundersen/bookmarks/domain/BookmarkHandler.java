package net.mariusgundersen.bookmarks.domain;

import java.util.List;

import qvc.handlers.Handler;

public class BookmarkHandler implements Handler {

	private BookmarkCollection bookmarks;
	
	
	
	public BookmarkHandler(BookmarkCollection bookmarks) {
		this.bookmarks = bookmarks;
	}



	public void handle(AddBookmark bookmark){
		bookmarks.addBookmark(new Bookmark(bookmark.url, bookmark.name));
	}
	
	public List<Bookmark> handle(GetAllBookmarks query){
		return bookmarks.getAllBookmarks();
	}



	@Override
	public void setSessionId(String sessionId) {
		// TODO Auto-generated method stub
	}
}
