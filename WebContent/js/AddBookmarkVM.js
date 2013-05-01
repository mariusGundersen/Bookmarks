define(["Bookmark", "ordnung/qvc", "events", "knockout"], function(Bookmark, qvc, proclaim, ko){
	function AddBookmarkVM(){
		var self = this;
		
		
		this.name = ko.observable("");
		this.url = ko.observable("");
		
		this.saveBookmark = qvc.createCommand("AddBookmark",{
			name: self.name,
			url: self.url
		}).success(function(){
			proclaim.aBookmarkIsAdded(self.name(), self.url());
			self.name("");
			self.url("");
		});
	}
	
	return AddBookmarkVM;
});