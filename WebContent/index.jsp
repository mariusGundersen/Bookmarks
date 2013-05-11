<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% session.setAttribute("csrfToken", java.util.UUID.randomUUID().toString()); %>
<!doctype html>
<title>Bookmarks</title>
<link rel="stylesheet" href="css/style.css">
<meta name="csrfToken" content="<%= session.getAttribute("csrfToken") %>">
<div data-viewmodel="AddBookmarkVM">
	
	<label>Title: <span class="error" data-bind="validationMessageFor:name"></span><br>
	<input data-bind="value:name, valueUpdate:'afterkeydown'" placeholder="enter the name of the link"></label>
	
	
	<label>URL: <span class="error" data-bind="validationMessageFor:url"></span><br>
	<input data-bind="value:url, valueUpdate:'afterkeydown'" placeholder="enter the url"></label>
	
	<span>Your link: </span><a data-bind="text:name, attr: {href: url}" target="_blank"></a><br>
	
	<button data-bind="command: saveBookmark">Save Bookmark</button><br>
	
</div>

<div data-viewmodel="ListBookmarksVM">
	
	<label><input data-bind="value:filter, valueUpdate:'afterkeydown'" placeholder="filter names"></label>
	<ul data-bind="foreach: filteredList">
		<li><a data-bind="text: name, attr:{href: url}" target="_blank"></a></li>
	</ul>
	
</div>

<script src="js/Libs/es5-shim.min.js"></script>
<script src="js/Libs/require-2.1.1.js" data-main="js/main.js"></script>
