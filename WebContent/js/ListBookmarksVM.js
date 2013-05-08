define(["Bookmark", "ordnung/qvc", "events", "knockout"], function(Bookmark, qvc, when, ko){


	function ListBookmarksVM(){
		var self = this;
		
		this.list = ko.observableArray();
		this.filter = ko.observable("");
		this.filteredList = ko.computed(function(){
			var filter = self.filter().toLowerCase();
			return self.list().filter(function(item){
				return item.name.toLowerCase().indexOf(filter) >= 0;
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
		
		function addTheBookmarkToTheList(name, url){
			self.addBookmark(url, name);
		}
		
		init: {
			self.getBookmarks();
			when.aBookmarkIsAdded(addTheBookmarkToTheList);
		}
	}
	
	return ListBookmarksVM;
});