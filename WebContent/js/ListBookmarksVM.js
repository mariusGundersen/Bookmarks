define(["Bookmark", "ordnung/qvc", "knockout"], function(Bookmark, qvc, ko){


	function ListBookmarksVM(){
		var self = this;
		
		this.list = ko.observableArray();
		this.filter = ko.observable("");
		this.filteredList = ko.computed(function(){
			var filter = self.filter();
			return self.list().filter(function(item){
				return item.name.indexOf(filter) >= 0;
			});
		});
		
		this.addBookmark = function(url, name){
			this.list.push(new Bookmark(url, name));
		};
		
		this.getBookmarks = qvc.createQuery("GetAllBookmarks").result(function(bookmarks){
			self.list(bookmarks.map(function(bookmark){
				return new Bookmark(bookmark.url, bookmark.name);
			}));
		});
		
		
		
		init: {
			self.getBookmarks();
		}
	}
	
	return ListBookmarksVM;
});