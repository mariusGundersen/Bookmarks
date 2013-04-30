package net.mariusgundersen.bookmarks.domain;

import java.util.*;

public class BookmarkCollection {
	private List<Bookmark> bookmarks = new ArrayList<>();
	
	
	public void addBookmark(Bookmark bookmark){
		bookmarks.add(bookmark);
	}
	
	public List<Bookmark> getAllBookmarks(){
		return bookmarks;
	}
}
