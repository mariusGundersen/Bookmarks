define(["ordnung/pubsub"], function(pubsub){
	var events = {
		aBookmarkIsAdded: function(name, url){}
	};
	
	return pubsub.extend(events);
});