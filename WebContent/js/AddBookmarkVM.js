define(["Bookmark", "ordnung/qvc", "knockout"], function(Bookmark, qvc, ko){
	function AddBookmarkVM(){
		var self = this;
		
		
		this.name = ko.observable("");
		this.url = ko.observable("");
		
		this.saveBookmark = qvc.createCommand("AddBookmark",{
			name: self.name,
			url: self.url
		}).success(function(){
			self.name("");
			self.url("");
		});
	}
	
	return AddBookmarkVM;
});